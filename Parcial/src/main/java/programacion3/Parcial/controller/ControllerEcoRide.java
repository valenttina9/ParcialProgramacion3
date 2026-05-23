package programacion3.Parcial.controller;

import programacion3.Parcial.service.ServiceEco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerEcoRide {

    @Autowired
    private ServiceEco alquilerService;


    @GetMapping("/vehiculos")
    public Object listarVehiculos() {

        return alquilerService.listarVehiculos();

    }


    @PostMapping("/alquilar")
    public String alquilar(

            @RequestParam Long idUsuario,

            @RequestParam String patente,

            @RequestParam String metodoPago

    ) {

        return alquilerService.alquilar(
                idUsuario,
                patente,
                metodoPago
        );

    }

}
