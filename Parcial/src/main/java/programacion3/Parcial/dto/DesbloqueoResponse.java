package programacion3.Parcial.dto;

public record DesbloqueoResponse(
        String patente,
        String tipoVehiculo,
        String estadoActual,
        String criterioTarifaAplicado,
        double montoCobrado,
        String mensajePago
) {
}
