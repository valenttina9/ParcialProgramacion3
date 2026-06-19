package programacion3.Parcial.dto;

import programacion3.Parcial.model.ReporteGps;

import java.util.List;

public record DepuracionGpsRequest(
        List<ReporteGps> reportes
) {
}
