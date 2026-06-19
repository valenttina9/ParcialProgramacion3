package programacion3.Parcial.model.vehiculo.estado;

import programacion3.Parcial.exception.EstadoVehiculoInvalidoException;
import programacion3.Parcial.model.vehiculo.Vehiculo;

// Esta clase representa el estado de espera de un vehículo
public class EnEsperaState implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnViajeState());
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("El vehiculo ya se encuentra en espera.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnReparacionState());
    }

    @Override
    public void pasarAEspera(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("El vehiculo ya se encuentra en espera.");
    }

    @Override
    public boolean puedeSerAlquilado() {
        return true;
    }

    @Override
    public String getNombre() {
        return "EN_ESPERA";
    }
}
