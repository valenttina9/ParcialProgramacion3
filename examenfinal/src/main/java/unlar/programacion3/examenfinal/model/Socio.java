package unlar.programacion3.examenfinal.model;

import lombok.Data;

@Data
public class Socio {
    private int id;
    private String nombre;
    // no hace falta una clase por tipo de socio, como son dos tipos de socio
    // podemos usar un booleano con if en el calculo de la multa (interface)
    private Boolean premium;
}
