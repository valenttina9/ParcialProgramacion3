package unlar.programacion3.examenfinal.model;

import lombok.Data;
import java.time.LocalDate; 

@Data

public class Prestamo {
    private int idSocio;
    private String codigoMaterial;
    private LocalDate fechaPrestamo;
}
