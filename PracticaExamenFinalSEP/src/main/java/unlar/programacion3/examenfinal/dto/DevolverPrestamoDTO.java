package unlar.programacion3.examenfinal.dto;

import java.time.LocalDate;

import lombok.Data;
@Data 

public class DevolverPrestamoDTO {
    private String codigoMaterial;
    private int idSocio;
    private LocalDate fechaDevolucion;
    private int diasAtraso;

    public DevolverPrestamoDTO(String codigoMaterial, int idSocio, LocalDate fechaDevolucion) {
        this.codigoMaterial = codigoMaterial;
        this.idSocio = idSocio;
        this.fechaDevolucion = fechaDevolucion;
        this.diasAtraso = 0; // Inicializar diasAtraso en 0
    }
}
