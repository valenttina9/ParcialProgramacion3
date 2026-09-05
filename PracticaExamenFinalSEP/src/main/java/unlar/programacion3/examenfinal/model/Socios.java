package unlar.programacion3.examenfinal.model;

import lombok.Data;

@Data
public abstract class Socios {
    private int id;
    private String nombre;

    // constructor
    public Socios(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // metodo abstracto para aplicar beneficio
    public abstract double aplicarBeneficio(double monto);
}
