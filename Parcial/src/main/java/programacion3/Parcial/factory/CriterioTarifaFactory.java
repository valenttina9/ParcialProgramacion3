package programacion3.Parcial.factory;

import org.springframework.stereotype.Component;
import programacion3.Parcial.exception.EcoRideException;
import programacion3.Parcial.strategy.tarifa.CriterioTarifa;
import programacion3.Parcial.strategy.tarifa.CriterioTarifaEstandar;
import programacion3.Parcial.strategy.tarifa.CriterioTarifaHoraPico;
import programacion3.Parcial.strategy.tarifa.CriterioTarifaTemporalClimatico;

@Component
public class CriterioTarifaFactory {

    public CriterioTarifa crear(String criterio) {
        String valorNormalizado = criterio == null ? "" : criterio.trim().toUpperCase();

        return switch (valorNormalizado) {
            case "ESTANDAR" -> new CriterioTarifaEstandar();
            case "HORA_PICO" -> new CriterioTarifaHoraPico();
            case "TEMPORAL_CLIMATICO" -> new CriterioTarifaTemporalClimatico();
            default -> throw new EcoRideException("Criterio de tarifa no soportado: " + criterio);
        };
    }
}
