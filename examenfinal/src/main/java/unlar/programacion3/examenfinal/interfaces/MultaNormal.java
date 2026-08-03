package unlar.programacion3.examenfinal.interfaces;

// multa normal vale 100$ por dia de retraso
public class MultaNormal implements CalculoMulta {
    @Override
    public double calcularMulta(int diasRetraso, boolean premium) {
        
        double tarifaPorDia = 100;
        double multaTotal= diasRetraso * tarifaPorDia;

        if (premium) {
            multaTotal = multaTotal * 0.5;
            return multaTotal;
        } else {
            return multaTotal;
        }
    }
}
