package unlar.programacion3.examenfinal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Revista extends Materiales {
    private int numEdicion;

    // constructor
    public Revista(String codigo, String titulo, boolean disponible, int numEdicion) {
        super(codigo, titulo, disponible);
        this.numEdicion = numEdicion;
    }
}
