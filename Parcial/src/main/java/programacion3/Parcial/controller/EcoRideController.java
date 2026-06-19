package programacion3.Parcial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import programacion3.Parcial.dto.AccionVehiculoResponse;
import programacion3.Parcial.dto.CambioCriterioTarifaResponse;
import programacion3.Parcial.dto.DesbloqueoRequest;
import programacion3.Parcial.dto.DesbloqueoResponse;
import programacion3.Parcial.dto.DepuracionGpsRequest;
import programacion3.Parcial.dto.DepuracionGpsResponse;
import programacion3.Parcial.model.EstacionAnclaje;
import programacion3.Parcial.model.vehiculo.Vehiculo;
import programacion3.Parcial.service.EcoRideService;
import java.util.List;
import programacion3.Parcial.dto.AlquilerRequest;

@RestController
@RequestMapping("/api")
public class EcoRideController {

    private final EcoRideService ecoRideService;

    public EcoRideController(EcoRideService ecoRideService) {
        this.ecoRideService = ecoRideService;
    }
    // Punto C  - Desafio API 
    @RequestMapping(value = "/alquileres/desbloquear", method = {RequestMethod.GET, RequestMethod.POST})
    public DesbloqueoResponse desbloquear(@RequestBody DesbloqueoRequest request) {
        return ecoRideService.desbloquear(request);
    }

    @GetMapping("/estaciones")
    public List<EstacionAnclaje> listarEstaciones() {
        return ecoRideService.listarEstaciones();
    }

    @PostMapping("/vehiculos/{patente}/iniciar-viaje")
    public AccionVehiculoResponse iniciarViaje(@PathVariable String patente) {
        return ecoRideService.iniciarViaje(patente);
    }


    // Punto C - Desafio API
    @PostMapping("/vehiculos/{patente}/finalizar-viaje")
    public AccionVehiculoResponse finalizarViaje(@PathVariable String patente) {
        return ecoRideService.finalizarViaje(patente);
    }

    @PostMapping("/vehiculos/{patente}/reparacion")
    public AccionVehiculoResponse enviarAReparacion(@PathVariable String patente) {
        return ecoRideService.enviarAReparacion(patente);
    }

    @PostMapping("/vehiculos/{patente}/espera")
    public AccionVehiculoResponse pasarAEspera(@PathVariable String patente) {
        return ecoRideService.marcarEnEspera(patente);
    }

    // Punto B - 3 Devuelve los vehículos ordenados de tarifa BASE de mayor a menor
    @PostMapping("/tarifas/criterio")
    public CambioCriterioTarifaResponse cambiarCriterioTarifa(@RequestParam String criterio) {
        return ecoRideService.cambiarCriterioTarifa(criterio);
    }

    // Punto B - 3 Devuelve la lista de vehículos con porcentaje de bateria de menor a mayor
    @GetMapping("/vehiculos/prioridad-carga")
    public List<Vehiculo> listarVehiculosPorPrioridadDeCarga() {
        return ecoRideService.listarVehiculosPorPrioridadDeCarga();
    }

    // Punto B - 2
    @PostMapping("/gps/depurar")
    public DepuracionGpsResponse depurarReportesGps(@RequestBody DepuracionGpsRequest request) {
        return ecoRideService.depurarReportesGps(request);
    }
}
