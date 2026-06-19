package programacion3.Parcial.dto;

public record AlquilerRequest(
        Long idUsuario,
        String patente,
        String metodoPago,
        int minutosViaje
) {
}