package unlar.programacion3.examenfinal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unlar.programacion3.examenfinal.service.PrestamoService;
import org.springframework.web.bind.annotation.GetMapping;

import unlar.programacion3.examenfinal.model.Socio;
import org.springframework.web.bind.annotation.RequestBody;
import unlar.programacion3.examenfinal.dto.RequestPrestarDTO;
import unlar.programacion3.examenfinal.dto.ResponsePrestarDTO;
import unlar.programacion3.examenfinal.dto.ResponseDevolverDTO;

import unlar.programacion3.examenfinal.model.Materiales;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/BiblioExpress")
public class PrestamoController {
    
    private final PrestamoService prestamoService;
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping("/api/materiales/disponibles")
    public List<Materiales> getMaterialesDisponibles() {
        return prestamoService.materialesDisponibles();
    }

    // entra un json con una lista de socios y devuelve la lista depurada (sin duplicados)
    @PostMapping("/api/socios/depurar-duplicados")
    public List<Socio> depurarSociosDuplicados(@RequestBody List<Socio> socios) {
        prestamoService.depurarSociosDuplicados();
        return prestamoService.obtenerSociosDepurados();
    }

    // para prestar un material a un socio, se recibe un json con el codigo del material y el id del socio
    @PostMapping("/api/prestamos/prestar")
    public ResponsePrestarDTO prestarMaterial(@RequestBody RequestPrestarDTO request) {
        return prestamoService.prestarMaterial(request);
    }
    
    // para devolver un material, se recibe un json con el codigo del material y el id del socio
    // ejemplo de json: {"codigoMaterial":"L001","idSocio":1}
    @PostMapping("/api/prestamos/devolver")
    public ResponseDevolverDTO devolverMaterial(@RequestBody RequestPrestarDTO request) {
        return prestamoService.devolverMaterial(request);
    }
    
    
    
}
