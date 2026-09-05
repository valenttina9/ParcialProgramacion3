package unlar.programacion3.examenfinal.service;

import org.springframework.stereotype.Service;

import unlar.programacion3.examenfinal.dto.RequestPrestarDTO;
import unlar.programacion3.examenfinal.model.Libro;
import unlar.programacion3.examenfinal.model.Materiales;
import unlar.programacion3.examenfinal.dto.ResponsePrestarDTO;
import unlar.programacion3.examenfinal.dto.ResponseDevolverDTO;
import unlar.programacion3.examenfinal.model.Socio;
import unlar.programacion3.examenfinal.interfaces.ProcesadorStrategy;
import jakarta.annotation.PostConstruct;
import unlar.programacion3.examenfinal.model.Revista;
import unlar.programacion3.examenfinal.model.Prestamo;
import unlar.programacion3.examenfinal.exceptions.MaterialNoEncontradoException;
import unlar.programacion3.examenfinal.exceptions.MaterialNoDisponibleException;
import unlar.programacion3.examenfinal.exceptions.SocioNoEncontradoException;
import unlar.programacion3.examenfinal.exceptions.PrestamoNoEncontradoException;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class PrestamoService {


    @PostConstruct
    public void init() {
        // Inicializar la lista de materiales (libros y revistas)
        Libro libro1 = new Libro();
        libro1.setCodigo("L001");
        libro1.setTitulo("El Quijote");
        libro1.setDisponible(true);
        libro1.setAutor("Miguel de Cervantes");

        materiales.add(libro1);

        Libro libro2 = new Libro();
        libro2.setCodigo("L002");
        libro2.setTitulo("Cien Años de Soledad");
        libro2.setDisponible(true);
        libro2.setAutor("Gabriel García Márquez");
        
        materiales.add(libro2);

        Revista revista1 = new Revista();
        revista1.setCodigo("R001");
        revista1.setTitulo("Revista de Ciencia");
        revista1.setDisponible(false);
        revista1.setNumeroEdicion(5);

        materiales.add(revista1);

        // Inicializar la lista de socios
        Socio socio1 = new Socio();
        socio1.setId(1);
        socio1.setNombre("Juan Pérez");
        socio1.setPremium(true);
        socios.add(socio1);
        Socio socio2 = new Socio();
        socio2.setId(2);
        socio2.setNombre("María Gómez");
        socio2.setPremium(false);
        socios.add(socio2);
    }
    private ProcesadorStrategy procesadorStrategy;
    public PrestamoService(ProcesadorStrategy procesadorStrategy) {
        this.procesadorStrategy = procesadorStrategy;
    }
    
    // listas para usar en los metodos de la clase
    private List<Materiales> materiales = new ArrayList<>(); // lista de materiales (libros y revistas)
    private Map<Integer, Socio> sociosNoDuplicados = new HashMap<>(); // mapa de socios para deduplicados
    private List<Socio> socios = new ArrayList<>(); // lista de socios para deduplicados
    private List<Prestamo> prestamos = new ArrayList<>(); // lista de préstamos activos (aún no devueltos)

    private static final int PLAZO_DIAS_PRESTAMO = 7; // días permitidos antes de generar multa

    // mostrar la lista de materiales disponibles (libros y revistas)
    public List<Materiales> materialesDisponibles() {

        List<Materiales> materialesDisponibles = new ArrayList<>();
        // defino los disponibles como true para filtrar los materiales disponibles
        Boolean disponible = true;

        for (Materiales material : materiales) {
            if (material.getDisponible().equals(disponible)) {
                materialesDisponibles.add(material);
            }
        }
        return materialesDisponibles;
    }

    // solicitar un material (libro o revista) por un socio
    public ResponsePrestarDTO prestarMaterial(RequestPrestarDTO request) {
        // buscamos por material y socio
        Materiales material = buscarMaterialPorCodigo(request.codigoMaterial());
        if (material == null) {
            throw new MaterialNoEncontradoException(request.codigoMaterial());
        }
        if (!material.getDisponible()) {
            throw new MaterialNoDisponibleException(request.codigoMaterial());
        }

        Socio socio = buscarSocioPorId(request.idSocio());
        if (socio == null) {
            throw new SocioNoEncontradoException(request.idSocio());
        }
        // si el material está disponible, se presta y se marca como no disponible
        material.setDisponible(false);

        Prestamo prestamo = new Prestamo();
        prestamo.setCodigoMaterial(request.codigoMaterial());
        prestamo.setIdSocio(request.idSocio());
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamos.add(prestamo);

        return new ResponsePrestarDTO(request.codigoMaterial(), request.idSocio(), "Material prestado con éxito");
    }

    // Devolver un material (libro o revista) por un socio
    public ResponseDevolverDTO devolverMaterial(RequestPrestarDTO request) {

        // buscamos el prestamo activo y verificamos que exista (excepciones)
        Prestamo prestamo = buscarPrestamoActivo(request.codigoMaterial(), request.idSocio());
        if (prestamo == null) {
            throw new PrestamoNoEncontradoException(request.codigoMaterial(), request.idSocio());
        }

        Materiales material = buscarMaterialPorCodigo(request.codigoMaterial());
        if (material == null) {
            throw new MaterialNoEncontradoException(request.codigoMaterial());
        }

        Socio socio = buscarSocioPorId(request.idSocio());
        if (socio == null) {
            throw new SocioNoEncontradoException(request.idSocio());
        }

        // calculo de dias de retraso: si hoy es posterior a la fecha limite, hay multa
        LocalDate hoy = LocalDate.now();
        LocalDate fechaLimite = prestamo.getFechaPrestamo().plusDays(PLAZO_DIAS_PRESTAMO);

        long diasDeDiferencia = hoy.toEpochDay() - fechaLimite.toEpochDay();
        int diasRetraso = 0;
        if (diasDeDiferencia > 0) {
            // (int) para convertir de long a int
            diasRetraso = (int) diasDeDiferencia;
        }

        // eleccion de la estrategia de multa (Strategy): si se devuelve en fin de semana, se aplica la tarifa de fin de semana
        String tipoMulta = "Normal";
        if (hoy.getDayOfWeek() == DayOfWeek.SATURDAY || hoy.getDayOfWeek() == DayOfWeek.SUNDAY) {
            tipoMulta = "FinDeSemana";
        }

        // calculo de la multa usando el procesadorStrategy (Strategy)
        double multa = 0;
        if (diasRetraso > 0) {
            multa = procesadorStrategy.calcularMulta(tipoMulta, diasRetraso, socio.getPremium());
        }

        // se devuelve el material y se elimina el prestamo de la lista de prestamos activos
        material.setDisponible(true);
        prestamos.remove(prestamo);

        return new ResponseDevolverDTO("Material devuelto con éxito", multa, request.idSocio());
    }

    // busca un material por su codigo, devuelve null si no existe
    private Materiales buscarMaterialPorCodigo(String codigoMaterial) {
        for (Materiales material : materiales) {
            if (material.getCodigo().equals(codigoMaterial)) {
                return material;
            }
        }
        return null;
    }

    // busca un socio por su id, devuelve null si no existe
    private Socio buscarSocioPorId(int idSocio) {
        for (Socio socio : socios) {
            if (socio.getId() == idSocio) {
                return socio;
            }
        }
        return null;
    }

    // busca un prestamo activo (no devuelto) por codigo de material y id de socio
    private Prestamo buscarPrestamoActivo(String codigoMaterial, int idSocio) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCodigoMaterial().equals(codigoMaterial) && prestamo.getIdSocio() == idSocio) {
                return prestamo;
            }
        }
        return null;
    }

    // Depurar socios duplicados en la lista de socios
    public void depurarSociosDuplicados() {
        for (Socio socio : socios) {
            sociosNoDuplicados.put(socio.getId(), socio);
        }
    }

    // Obtener la lista de socios depurados (sin duplicados)
    public List<Socio> obtenerSociosDepurados() {
        return new ArrayList<>(sociosNoDuplicados.values());
    }

    }

