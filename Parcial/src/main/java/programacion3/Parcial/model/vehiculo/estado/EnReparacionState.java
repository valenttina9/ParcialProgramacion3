package programacion3.Parcial.model.vehiculo.estado;

import programacion3.Parcial.exception.EstadoVehiculoInvalidoException;
import programacion3.Parcial.model.vehiculo.Vehiculo;

public class EnReparacionState implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("No se puede iniciar un viaje con un vehiculo en reparacion.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("El vehiculo no se encuentra en viaje.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new EstadoVehiculoInvalidoException("El vehiculo ya se encuentra en reparacion.");
    }

    @Override
    public void pasarAEspera(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnEsperaState());
    }

    @Override
    public boolean puedeSerAlquilado() {
        return false;
    }

    @Override
    public String getNombre() {
        return "EN_REPARACION";
    }
}
