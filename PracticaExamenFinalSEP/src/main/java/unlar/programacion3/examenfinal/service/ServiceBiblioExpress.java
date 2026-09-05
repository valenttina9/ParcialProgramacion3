package unlar.programacion3.examenfinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import unlar.programacion3.examenfinal.model.Materiales;
import unlar.programacion3.examenfinal.model.Socios;
import unlar.programacion3.examenfinal.model.SocioPremium;
import unlar.programacion3.examenfinal.model.SocioRegular;
import unlar.programacion3.examenfinal.model.Prestamo;
import unlar.programacion3.examenfinal.dto.ResponsePrestamoDTO;
import unlar.programacion3.examenfinal.dto.DevolverPrestamoDTO;
import unlar.programacion3.examenfinal.dto.RequestPrestamoDTO;
import unlar.programacion3.examenfinal.exceptions.SocioNoEncontrado;
import unlar.programacion3.examenfinal.exceptions.MaterialNoEncontrado;
import unlar.programacion3.examenfinal.dto.MultaUsuarioDTO;
import unlar.programacion3.examenfinal.dto.SocioDuplicadoDTO;
import unlar.programacion3.examenfinal.strategy.MultaStrategy;
import unlar.programacion3.examenfinal.strategy.MultaNormal;
import unlar.programacion3.examenfinal.repository.MaterialRepository;

@Service
public class ServiceBiblioExpress {

    private final MaterialRepository materialRepository;

    private List<Socios> sociosRegistrados = new ArrayList<>(List.of(
            new SocioRegular(1, "Ana Torres"),
            new SocioRegular(2, "Bruno Diaz"),
            new SocioPremium(3, "Carla Ruiz"),
            new SocioPremium(4, "Diego Fernandez")
    ));

    private Set<Socios> sociosNoDuplicados = new HashSet<>();

    private List<Prestamo> prestamosActivos = new ArrayList<>();

    public ServiceBiblioExpress(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    // mostrar los materiales disponibles
    public List<Materiales> getMaterialesDisponibles() {
        List<Materiales> disponibles = new ArrayList<>();
        for (Materiales material : materialRepository.getMateriales()) {
            if (material.getDisponible()) {
                disponibles.add(material);
            }
        }
        return disponibles;
    }

    // mostrar los socios registrados
    public List<Socios> getSociosRegistrados() {
        return new ArrayList<>(sociosRegistrados);
    }

    // buscar socios por id
    public Socios buscarSocioPorId(int id) {
        for (Socios socio : sociosRegistrados) {
            if (socio.getId() == id) {
                return socio;
            }
        }
        return null;
    }

    // buscar material por codigo
    public Materiales buscarMaterialPorCodigo(String codigo) {
        for (Materiales material : materialRepository.getMateriales()) {
            if (material.getCodigo().equals(codigo)) {
                return material;
            }
        }
        return null;
    }

    // pedir prestamo de un material
    public ResponsePrestamoDTO pedirPrestamo (RequestPrestamoDTO request) {

        Socios socio = buscarSocioPorId(request.getIdSocio());
        Materiales material = buscarMaterialPorCodigo(request.getCodigoMaterial());

        if (socio == null) {
            throw new SocioNoEncontrado();
        }

        if (material == null || !material.getDisponible()) {
            throw new MaterialNoEncontrado();
        }

    Prestamo prestamo = new Prestamo();
    prestamo.setSocio(socio);
    prestamo.setMaterial(material);
    prestamo.setIdPrestamo(prestamosActivos.size() + 1); // Asignar un ID único al préstamo

    prestamosActivos.add(prestamo);
    material.setDisponible(false);

    return new ResponsePrestamoDTO(material.getCodigo(), socio.getId(),LocalDate.now().toString(), prestamo.getIdPrestamo());
    }

    // busca prestamos activos por socio
    public List<Prestamo> buscarPrestamosPorSocio(int idSocio) {
        List<Prestamo> prestamosSocio = new ArrayList<>();
        for (Prestamo prestamo : prestamosActivos) {
            if (prestamo.getSocio().getId() == idSocio) {
                prestamosSocio.add(prestamo);
            }
        }
        return prestamosSocio;
    }

    // devolver un material prestado
    public MultaUsuarioDTO devolverMaterial(DevolverPrestamoDTO request) {
        List<Prestamo> socio = buscarPrestamosPorSocio(request.getIdSocio());

        if (socio.isEmpty()) {
            throw new SocioNoEncontrado();
        }

        Prestamo prestamoEncontrado = null;
        for (Prestamo prestamo : socio) {
            if (prestamo.getMaterial().getCodigo().equals(request.getCodigoMaterial())) {
                prestamoEncontrado = prestamo;
                break;
            }
        }

        if (prestamoEncontrado == null) {
            throw new MaterialNoEncontrado();
        }

        prestamosActivos.remove(prestamoEncontrado);
        prestamoEncontrado.getMaterial().setDisponible(true);

        int diasAtraso = request.getDiasAtraso();
        double monto = 0.0;

        if (diasAtraso > 0) {
            MultaStrategy multaStrategy = new MultaNormal();
            double montoBase = multaStrategy.calcularMulta(diasAtraso);
            monto = prestamoEncontrado.getSocio().aplicarBeneficio(montoBase);
        }

        return new MultaUsuarioDTO(monto, request.getIdSocio(), diasAtraso);
    }

    // lista de socios no duplicados
    // Depurar socios duplicados en la lista de socios
    public void depurarSociosDuplicados() {
        for (Socios socio : sociosRegistrados) {
            sociosNoDuplicados.add(socio);
        }
        }

    // Obtener la lista de socios depurados (sin duplicados)
    public List<Socios> obtenerSociosDepurados() {
        return new ArrayList<>(sociosNoDuplicados);
    }

    // Depurar duplicados de una lista de socios recibida por request (por id)
    public List<SocioDuplicadoDTO> depurarDuplicados(List<SocioDuplicadoDTO> socios) {
        List<SocioDuplicadoDTO> depurados = new ArrayList<>();
        Set<Integer> idsVistos = new HashSet<>();
        for (SocioDuplicadoDTO socio : socios) {
            if (idsVistos.add(socio.id())) {
                depurados.add(socio);
            }
        }
        return depurados;
    }

}