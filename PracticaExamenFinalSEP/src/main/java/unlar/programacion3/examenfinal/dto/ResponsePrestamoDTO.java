package unlar.programacion3.examenfinal.dto;

import lombok.Data;

@Data
public class ResponsePrestamoDTO {
    private String codigoMaterial;
    private int idSocio;
    private String fechaPrestamo;
    private int idPrestamo;

    public ResponsePrestamoDTO(String codigoMaterial, int idSocio, String fechaPrestamo, int idPrestamo) {
        this.codigoMaterial = codigoMaterial;
        this.idSocio = idSocio;
        this.fechaPrestamo = fechaPrestamo;
        this.idPrestamo = idPrestamo;
    }
}
