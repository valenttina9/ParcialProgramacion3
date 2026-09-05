package unlar.programacion3.examenfinal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Libro extends Materiales {
    private String autor;

    // constructor
    public Libro(String codigo, String titulo, Boolean disponible, String autor) {
        super(codigo, titulo, disponible);
        this.autor = autor;
    }
}

