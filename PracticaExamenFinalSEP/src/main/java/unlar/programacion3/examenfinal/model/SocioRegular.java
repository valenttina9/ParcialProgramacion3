package unlar.programacion3.examenfinal.model;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class SocioRegular extends Socios {

    // aplicamos el metodo abstracto de la clase padre
    @Override 
    public double aplicarBeneficio(double monto) {
        // SocioRegular no tiene beneficio
        return monto;
    }

    // constructor
    public SocioRegular(Integer id, String nombre) {
        super(id, nombre);
    }
}
