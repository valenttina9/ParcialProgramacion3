package programacion3.Parcial.strategy.tarifa;

import programacion3.Parcial.model.vehiculo.Vehiculo;

public class CriterioTarifaTemporalClimatico implements CriterioTarifa {

    @Override

    // Calcular monto es por minuto del alquiler
    public double calcularMonto(Vehiculo vehiculo, int minutosViaje) {
        double montoBase = vehiculo.getTarifaBase() * minutosViaje;
        return montoBase + 150; // Suma un recargo de 150$ por el seguro de lluvia o tornmenta
    }

    @Override
    public String getNombre() {
        return "TEMPORAL_CLIMATICO";
    }
}
