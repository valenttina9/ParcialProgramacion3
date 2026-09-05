package unlar.programacion3.examenfinal.controller;

import org.springframework.web.bind.annotation.RestController;

import unlar.programacion3.examenfinal.model.Materiales;
import unlar.programacion3.examenfinal.service.ServiceBiblioExpress;
import unlar.programacion3.examenfinal.dto.RequestPrestamoDTO;
import unlar.programacion3.examenfinal.dto.ResponsePrestamoDTO;
import unlar.programacion3.examenfinal.dto.DevolverPrestamoDTO;
import unlar.programacion3.examenfinal.dto.MultaUsuarioDTO;
import unlar.programacion3.examenfinal.dto.SocioDuplicadoDTO;
import unlar.programacion3.examenfinal.model.Socios;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/BiblioExpress")
public class ControllerBiblioApiExpress {

    private final ServiceBiblioExpress serviceBiblioExpress;
    public ControllerBiblioApiExpress(ServiceBiblioExpress serviceBiblioExpress) {
        this.serviceBiblioExpress = serviceBiblioExpress;
    }

    // Endpoint para obtener los materiales disponibles
    @GetMapping("/api/materiales/disponibles")
    public List<Materiales> getMaterialesDisponibles() {
        return serviceBiblioExpress.getMaterialesDisponibles();
    }

    // Endpoint para obtener los socios registrados
    @GetMapping("/api/socios")
    public List<Socios> getSociosRegistrados() {
        return serviceBiblioExpress.getSociosRegistrados();
    }

    // Endpoint para pedir prestamo de un material
    @PostMapping("/api/presamos/prestar")
    public ResponsePrestamoDTO pedirPrestamo(@RequestBody RequestPrestamoDTO request) {
        return serviceBiblioExpress.pedirPrestamo(request);
    }

    // Endpoint para devolver un material prestado y calcular la multa (si aplica)
    @PostMapping("/api/prestamos/devolver")
    public MultaUsuarioDTO devolverMaterial(@RequestBody DevolverPrestamoDTO request) {
        return serviceBiblioExpress.devolverMaterial(request);
    }

    // Endpoint para depurar socios duplicados (por id) a partir del body recibido
    @PostMapping("/api/socios/depurar-duplicados")
    public List<SocioDuplicadoDTO> depurarSociosDuplicados(@RequestBody List<SocioDuplicadoDTO> socios) {
        return serviceBiblioExpress.depurarDuplicados(socios);
    }

}
