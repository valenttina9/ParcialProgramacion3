package programacion3.Parcial.model.usuario;

public abstract class Usuario {

    private Long id;
    private String nombreCompleto;

    protected Usuario() {
    }

    protected Usuario(Long id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public abstract double aplicarBeneficio(double montoBase);
}
