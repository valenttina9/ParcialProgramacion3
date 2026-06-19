package programacion3.Parcial.model.vehiculo;

public class BicicletaElectrica extends Vehiculo {

    private int capacidadCanastoCm3;

    public BicicletaElectrica() {
    }

    public BicicletaElectrica(String patente, int porcentajeBateria, double tarifaBase, int capacidadCanastoCm3) {
        super(patente, porcentajeBateria, tarifaBase);
        this.capacidadCanastoCm3 = capacidadCanastoCm3;
    }

    public int getCapacidadCanastoCm3() {
        return capacidadCanastoCm3;
    }

    public void setCapacidadCanastoCm3(int capacidadCanastoCm3) {
        this.capacidadCanastoCm3 = capacidadCanastoCm3;
    }

    @Override
    public String getTipo() {
        return "Bicicleta electrica";
    }
}
