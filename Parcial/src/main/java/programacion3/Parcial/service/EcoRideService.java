package programacion3.Parcial.service;

import org.springframework.stereotype.Service;
import programacion3.Parcial.dto.AccionVehiculoResponse;
import programacion3.Parcial.dto.CambioCriterioTarifaResponse;
import programacion3.Parcial.dto.DesbloqueoRequest;
import programacion3.Parcial.dto.DesbloqueoResponse;
import programacion3.Parcial.dto.DepuracionGpsRequest;
import programacion3.Parcial.dto.DepuracionGpsResponse;
import programacion3.Parcial.exception.BateriaInsuficienteException;
import programacion3.Parcial.exception.EcoRideException;
import programacion3.Parcial.exception.UsuarioNoEncontradoException;
import programacion3.Parcial.exception.VehiculoNoEncontradoException;
import programacion3.Parcial.factory.CriterioTarifaFactory;
import programacion3.Parcial.factory.ProcesadorPagoFactory;
import programacion3.Parcial.model.EstacionAnclaje;
import programacion3.Parcial.model.ReporteGps;
import programacion3.Parcial.model.pago.ProcesadorPago;
import programacion3.Parcial.model.usuario.Usuario;
import programacion3.Parcial.model.usuario.UsuarioPremium;
import programacion3.Parcial.model.usuario.UsuarioRegular;
import programacion3.Parcial.model.vehiculo.BicicletaElectrica;
import programacion3.Parcial.model.vehiculo.Monopatin;
import programacion3.Parcial.model.vehiculo.Vehiculo;
import programacion3.Parcial.strategy.tarifa.CriterioTarifa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EcoRideService {

    private final Map<Long, Usuario> usuariosPorId;
    private final List<EstacionAnclaje> estaciones;
    private final Map<String, Vehiculo> vehiculosPorPatente;
    private final ProcesadorPagoFactory procesadorPagoFactory;
    private final CriterioTarifaFactory criterioTarifaFactory;
    private CriterioTarifa criterioTarifaActivo;

    public EcoRideService(
            ProcesadorPagoFactory procesadorPagoFactory,
            CriterioTarifaFactory criterioTarifaFactory
    ) {
        this.procesadorPagoFactory = procesadorPagoFactory;
        this.criterioTarifaFactory = criterioTarifaFactory;
        this.criterioTarifaActivo = criterioTarifaFactory.crear("ESTANDAR");
        this.usuariosPorId = new HashMap<>();
        this.estaciones = new ArrayList<>();
        this.vehiculosPorPatente = new HashMap<>();
        cargarDatosIniciales();
    }

    public DesbloqueoResponse desbloquear(DesbloqueoRequest request) {
        Usuario usuario = buscarUsuarioPorId(request.idUsuario());
        Vehiculo vehiculo = buscarVehiculoPorPatente(request.patente());
        validarMinutos(request.minutosViaje());

        if (!vehiculo.tieneBateriaSuficiente()) {
            vehiculo.enviarAReparacion();
            throw new BateriaInsuficienteException("Bateria insuficiente.");
        }

        vehiculo.iniciarViaje();

        double montoSegunCriterio = criterioTarifaActivo.calcularMonto(vehiculo, request.minutosViaje());
        double montoFinal = usuario.aplicarBeneficio(montoSegunCriterio);
        ProcesadorPago procesadorPago = procesadorPagoFactory.crear(request.metodoPago());
        String mensajePago = procesadorPago.cobrar(montoFinal);

        return new DesbloqueoResponse(
                vehiculo.getPatente(),
                vehiculo.getTipo(),
                vehiculo.getEstadoActual(),
                criterioTarifaActivo.getNombre(),
                montoFinal,
                mensajePago
        );
    }

    public AccionVehiculoResponse finalizarViaje(String patente) {
        Vehiculo vehiculo = buscarVehiculoPorPatente(patente);
        vehiculo.finalizarViaje();
        return new AccionVehiculoResponse(vehiculo.getPatente(), vehiculo.getEstadoActual(), "Viaje finalizado.");
    }

    public AccionVehiculoResponse enviarAReparacion(String patente) {
        Vehiculo vehiculo = buscarVehiculoPorPatente(patente);
        vehiculo.enviarAReparacion();
        return new AccionVehiculoResponse(vehiculo.getPatente(), vehiculo.getEstadoActual(), "Vehiculo enviado a reparacion.");
    }

    public AccionVehiculoResponse marcarEnEspera(String patente) {
        Vehiculo vehiculo = buscarVehiculoPorPatente(patente);
        vehiculo.pasarAEspera();
        return new AccionVehiculoResponse(vehiculo.getPatente(), vehiculo.getEstadoActual(), "Vehiculo disponible nuevamente.");
    }

    public CambioCriterioTarifaResponse cambiarCriterioTarifa(String criterio) {
        criterioTarifaActivo = criterioTarifaFactory.crear(criterio);
        return new CambioCriterioTarifaResponse(
                criterioTarifaActivo.getNombre(),
                "Criterio de tarifa actualizado correctamente."
        );
    }

    public List<EstacionAnclaje> listarEstaciones() {
        return estaciones;
    }

    public List<Vehiculo> listarVehiculosPorPrioridadDeCarga() {
        List<Vehiculo> vehiculosOrdenados = new ArrayList<>(vehiculosPorPatente.values());
        Collections.sort(vehiculosOrdenados);
        return vehiculosOrdenados;
    }

    public List<Vehiculo> listarVehiculos() {
        return new ArrayList<>(vehiculosPorPatente.values());
    }

    // Punto B - 2 - El depurador (Deduplicacion) de GPS se encarga de eliminar los reportes duplicados
    public DepuracionGpsResponse depurarReportesGps(DepuracionGpsRequest request) {
        List<ReporteGps> reportes = request.reportes() == null ? List.of() : request.reportes();
        Set<ReporteGps> unicos = new LinkedHashSet<>();

        for (ReporteGps reporte : reportes) {
            unicos.add(reporte);
        }

        List<ReporteGps> reportesUnicos = new ArrayList<>(unicos);
        return new DepuracionGpsResponse(reportes.size(), reportesUnicos.size(), reportesUnicos);
    }

    private Usuario buscarUsuarioPorId(Long idUsuario) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        if (usuario != null) {
            return usuario;
        }
        throw new UsuarioNoEncontradoException("Usuario no encontrado con id " + idUsuario);
    }

    // Punto B - Buscar vehiculo por patente dentro de la ciudad
    private Vehiculo buscarVehiculoPorPatente(String patente) {
        Vehiculo vehiculo = vehiculosPorPatente.get(normalizarPatente(patente));
        if (vehiculo != null) {
            return vehiculo;
        }
        throw new VehiculoNoEncontradoException("Vehiculo no encontrado con patente " + patente);
    }

    private void validarMinutos(int minutosViaje) {
        if (minutosViaje <= 0) {
            throw new EcoRideException("La duracion del viaje debe ser mayor a cero.");
        }
    }
    
    // Para la busqueda de vehiculos por patente
    private String normalizarPatente(String patente) {
        return patente == null ? "" : patente.trim().toUpperCase();
    }

    private void registrarVehiculo(EstacionAnclaje estacion, Vehiculo vehiculo) {
        estacion.agregarVehiculo(vehiculo);
        vehiculosPorPatente.put(normalizarPatente(vehiculo.getPatente()), vehiculo);
    }

    // Carga de datos iniciales para la api
    private void cargarDatosIniciales() {
        usuariosPorId.put(1L, new UsuarioRegular(1L, "Juan Perez"));
        usuariosPorId.put(2L, new UsuarioPremium(2L, "Maria Gomez", 10));

        EstacionAnclaje estacionCentro = new EstacionAnclaje(1);
        registrarVehiculo(estacionCentro, new Monopatin("MONO123", 80, 5000, true));
        registrarVehiculo(estacionCentro, new BicicletaElectrica("BICI456", 50, 4500, 3200));

        EstacionAnclaje estacionNorte = new EstacionAnclaje(2);
        registrarVehiculo(estacionNorte, new Monopatin("MONO999", 10, 4000, false));

        estaciones.add(estacionCentro);
        estaciones.add(estacionNorte);
    }
}
