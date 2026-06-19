package programacion3.Parcial.dto;

import programacion3.Parcial.model.ReporteGps;

import java.util.List;

public record DepuracionGpsResponse(
        int cantidadOriginal,
        int cantidadUnica,
        // Punto B - 2 - El depurador (Deduplicacion) de GPS se encarga de eliminar los reportes duplicados
        List<ReporteGps> reportesUnicos
) {
}
