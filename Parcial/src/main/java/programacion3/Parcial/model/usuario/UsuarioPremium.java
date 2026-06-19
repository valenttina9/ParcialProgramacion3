package programacion3.Parcial.model.usuario;

public class UsuarioPremium extends Usuario {

    private double porcentajeDescuento;

    public UsuarioPremium() {
    }

    public UsuarioPremium(Long id, String nombreCompleto, double porcentajeDescuento) {
        super(id, nombreCompleto);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double aplicarBeneficio(double montoBase) {
        return montoBase - (montoBase * porcentajeDescuento / 100);
    }
}
