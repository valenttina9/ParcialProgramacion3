package unlar.programacion3.examenfinal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocioPremium extends Socios {

    // aplicamos el metodo abstracto de la clase padre
    @Override
    public double aplicarBeneficio(double monto) {
        // SocioPremium tiene un beneficio del 20%
        return monto * 0.5;
    }

    // constructor
    public SocioPremium(Integer id, String nombre) {
        super(id, nombre);
    }
}
