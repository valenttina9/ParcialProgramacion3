package unlar.programacion3.examenfinal.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import unlar.programacion3.examenfinal.dto.RequestPrestarDTO;
import unlar.programacion3.examenfinal.model.Libro;
import unlar.programacion3.examenfinal.model.Materiales;
import unlar.programacion3.examenfinal.dto.ResponsePrestarDTO;
import unlar.programacion3.examenfinal.dto.RequestPrestarDTO;
import unlar.programacion3.examenfinal.dto.ResponseDevolverDTO;
import unlar.programacion3.examenfinal.model.Socio;
import unlar.programacion3.examenfinal.interfaces.ProcesadorStrategy;
import jakarta.annotation.PostConstruct;
import unlar.programacion3.examenfinal.model.Revista;
import unlar.programacion3.examenfinal.interfaces.MultaNormal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

@Service
public class PrestamoService {


    @PostConstruct
    public void init() {
        // Inicializar la lista de materiales (libros y revistas)
        Libro libro1 = new Libro();
        libro1.setCodigo("L001");
        libro1.setTitulo("El Quijote");
        libro1.setDisponible(true);
        
        materiales.add(libro1);
        Libro libro2 = new Libro();
        libro2.setCodigo("L002");
        libro2.setTitulo("Cien Años de Soledad");
        libro2.setDisponible(true);
        
        materiales.add(libro2);
        Revista revista1 = new Revista();
        revista1.setCodigo("R001");
        revista1.setTitulo("Revista de Ciencia");
        revista1.setDisponible(true);

        
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
    
    private List<Materiales> materiales = new ArrayList<>(); // lista de materiales (libros y revistas)
    private Map<Integer, Socio> sociosNoDuplicados = new HashMap<>(); // mapa de socios para deduplicados
    private List<Socio> socios = new ArrayList<>(); // lista de socios para deduplicados

    // mostrar la lista de materiales disponibles (libros y revistas)
    public List<Materiales> materialesDisponibles() {
        return materiales;
    }

    // solicitar un material (libro o revista) por un socio
    public ResponsePrestarDTO prestarMaterial(RequestPrestarDTO request) {
        return new ResponsePrestarDTO(request.codigoMaterial(), request.idSocio(), java.time.LocalDate.now(), "Material prestado con éxito");
    }

    // Devolver un material (libro o revista) por un socio
    public ResponseDevolverDTO devolverMaterial(RequestPrestarDTO request) {
        double multa = procesadorStrategy.calcularMulta(new MultaNormal(), 5, true); // ejemplo de cálculo de multa
        return new ResponseDevolverDTO("Material devuelto con éxito", multa, request.idSocio());
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

