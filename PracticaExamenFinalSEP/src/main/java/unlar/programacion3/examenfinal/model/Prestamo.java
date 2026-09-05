package unlar.programacion3.examenfinal.model;

import lombok.Data;

@Data 
public class Prestamo {
    private int idPrestamo;
    private Socios socio;
    private Materiales material;
}
