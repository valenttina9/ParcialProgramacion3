package programacion3.Parcial.model;


public record ReporteGps(
        String patente,
        double latitud,
        double longitud,
        long timestamp
) {
}
