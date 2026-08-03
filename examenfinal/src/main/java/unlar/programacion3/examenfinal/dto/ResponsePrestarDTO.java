package unlar.programacion3.examenfinal.dto;

import java.time.LocalDate;

public record ResponsePrestarDTO(
    String codigoMaterial,
    int idSocio,
    LocalDate fechaPrestamo,
    String mensaje
) {
    
}
