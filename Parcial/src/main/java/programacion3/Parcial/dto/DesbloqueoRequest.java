package programacion3.Parcial.dto;

public record DesbloqueoRequest(
        Long idUsuario,
        String patente,
        String metodoPago,
        int minutosViaje
) {
}
