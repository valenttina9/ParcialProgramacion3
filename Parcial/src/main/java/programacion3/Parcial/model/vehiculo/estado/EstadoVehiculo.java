package programacion3.Parcial.model.vehiculo.estado;

import programacion3.Parcial.model.vehiculo.Vehiculo;

// Esta interfaz se aplica al patrón State para representar el ciclo de vida de un vehículo
// EnEspera, EnViaje, EnReparacion

public interface EstadoVehiculo {

    void iniciarViaje(Vehiculo vehiculo);

    void finalizarViaje(Vehiculo vehiculo);

    void enviarAReparacion(Vehiculo vehiculo);

    void pasarAEspera(Vehiculo vehiculo);

    boolean puedeSerAlquilado();

    String getNombre();
}
