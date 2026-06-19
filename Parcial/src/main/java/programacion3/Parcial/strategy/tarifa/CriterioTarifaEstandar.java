package programacion3.Parcial.strategy.tarifa;

import programacion3.Parcial.model.vehiculo.Vehiculo;

public class CriterioTarifaEstandar implements CriterioTarifa {

    @Override
    // Calcular monto es por minuto del alquiler
    public double calcularMonto(Vehiculo vehiculo, int minutosViaje) {
        return vehiculo.getTarifaBase() * minutosViaje;
    }

    @Override
    public String getNombre() {
        return "ESTANDAR";
    }
}
