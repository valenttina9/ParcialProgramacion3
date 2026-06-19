package programacion3.Parcial.model.usuario;

public class UsuarioRegular extends Usuario {

    public UsuarioRegular() {
    }

    public UsuarioRegular(Long id, String nombreCompleto) {
        super(id, nombreCompleto);
    }

    @Override
    public double aplicarBeneficio(double montoBase) {
        return montoBase;
    }
}
