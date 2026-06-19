package programacion3.Parcial.strategy.tarifa;

import programacion3.Parcial.model.vehiculo.Vehiculo;

// Esta interfaz se aplica al patrón Strategy para representar diferentes criterios de tarifa
// CriterioEstandar, CriterioHoraPico y CriterioClima
public interface CriterioTarifa {

    // Calcular monto es por minuto del alquiler
    double calcularMonto(Vehiculo vehiculo, int minutosViaje);

    String getNombre();
}
