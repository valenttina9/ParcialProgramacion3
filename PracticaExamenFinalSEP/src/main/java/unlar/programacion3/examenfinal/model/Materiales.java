package unlar.programacion3.examenfinal.model;

import lombok.Data;

@Data 
public abstract class Materiales {
    private String codigo;
    private String titulo;
    private Boolean disponible;

    // constructor
    public Materiales(String codigo, String titulo, Boolean disponible) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponible = disponible;
    }
}
