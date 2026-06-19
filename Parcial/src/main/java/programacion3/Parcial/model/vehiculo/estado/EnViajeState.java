package programacion3.Parcial.model.vehiculo.estado;

import programacion3.Parcial.exception.EstadoVehiculoInvalidoException;
import programacion3.Parcial.model.vehiculo.Vehiculo;

public class EnViajeState implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("El vehiculo ya se encuentra en viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnEsperaState());
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("No se puede enviar a reparacion un vehiculo en viaje.");
    }

    @Override
    public void pasarAEspera(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("Primero debe finalizar el viaje antes de volver a espera.");
    }

    @Override
    public boolean puedeSerAlquilado() {
        return false;
    }

    @Override
    public String getNombre() {
        return "EN_VIAJE";
    }
}
