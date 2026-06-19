package programacion3.Parcial.model;

import programacion3.Parcial.model.vehiculo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class EstacionAnclaje {

    private Integer numero;
    private final List<Vehiculo> vehiculosDisponibles;

    public EstacionAnclaje(Integer numero) {
        this.numero = numero;
        this.vehiculosDisponibles = new ArrayList<>();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<Vehiculo> getVehiculosDisponibles() {
        return vehiculosDisponibles;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculosDisponibles.add(vehiculo);
    }

    public Vehiculo buscarVehiculoPorPatente(String patente) {
        for (Vehiculo vehiculo : vehiculosDisponibles) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                return vehiculo;
            }
        }
        return null;
    }
}
