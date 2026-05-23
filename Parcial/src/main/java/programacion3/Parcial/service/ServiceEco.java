package programacion3.Parcial.service;

import programacion3.Parcial.model.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceEco {

    private EstacionesdeAnclaje estacion;
    private List<Usuarios> usuarios;


    public ServiceEco() {

        estacion = new EstacionesdeAnclaje();   
        usuarios = new ArrayList<>();

        // ESTACION
        estacion.setNombre("Estacion Central");
        estacion.setVehiculos(
                new ArrayList<>()
        );

        // VEHICULO 1
        Monopatines m = new Monopatines();
        m.setPatente("AAA111");
        m.setBateria(80);
        m.setTarifaBase(5000);
        m.setAmortiguacionReforzada(true);
        
        // VEHICULO 2
        BicicletaElectrica b = new BicicletaElectrica();
        b.setPatente("BBB222");
        b.setBateria(50);
        b.setTarifaBase(3000);
        b.setCapacidadCanasto(20);
        estacion.getVehiculos().add(m);
        estacion.getVehiculos().add(b);

        // USUARIO REGULAR
        UsuarioRegular u1 = new UsuarioRegular();

        u1.setId("1");
        u1.setNombreCompleto("Juan");

        // USUARIO PREMIUM
        UsuarioPremium u2 = new UsuarioPremium();

        u2.setId("2");  
        u2.setNombreCompleto("Maria");
        u2.setDescuento( 10);

        usuarios.add(u1);
        usuarios.add(u2);
    }

    public List<Vehiculo>
    listarVehiculos() {
        return estacion.getVehiculos();
    }

    public String alquilar(Long id, String patente, String metodoPago) {
        Usuarios usuario =
                usuarios
                    .stream()
                    .filter(u -> u.getId().equals(id.toString()))
                    .findFirst()
                    .orElse(null);

        if (usuario== null) {
            return
            "Usuario no encontrado";
        }

        Vehiculo vehiculo =
                estacion
                        .getVehiculos()
                        .stream()
                        .filter(v -> v.getPatente().equalsIgnoreCase(patente)
                        )
                        .findFirst()
                        .orElse(null);
        if (
                vehiculo == null
        ) {
            return
            "Vehiculo no encontrado";
        }

        if (
                vehiculo.getBateria()<15
        ) {
            return
            "Bateria insuficiente para circular";

        }

        double monto = vehiculo.getTarifaBase();

        if (
                usuario
                        instanceof
                        UsuarioPremium
        ) {
            monto = monto - ( monto  * ((UsuarioPremium) usuario) .getDescuento() / 100 );
        }
        return
                "Vehiculo " + vehiculo.getPatente() + " desbloqueado. Pago realizado: $"+monto;

    }

}
