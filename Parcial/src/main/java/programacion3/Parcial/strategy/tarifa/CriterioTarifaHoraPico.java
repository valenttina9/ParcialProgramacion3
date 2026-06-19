package programacion3.Parcial.strategy.tarifa;

import programacion3.Parcial.model.vehiculo.Vehiculo;

public class CriterioTarifaHoraPico implements CriterioTarifa {

    @Override

    // Calcular monto es por minuto del alquiler
    public double calcularMonto(Vehiculo vehiculo, int minutosViaje) {
        double montoBase = vehiculo.getTarifaBase() * minutosViaje;
        return montoBase * 1.40; // Aplica un aumento del 40%
    }

    @Override
    public String getNombre() {
        return "HORA_PICO";
    }
}
