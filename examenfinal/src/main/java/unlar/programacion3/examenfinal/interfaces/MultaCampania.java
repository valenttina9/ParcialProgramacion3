package unlar.programacion3.examenfinal.interfaces;

// multa por campaña, vale 60$ por dia de retraso
public class MultaCampania implements CalculoMulta {
    @Override
    public double calcularMulta(int diasRetraso, boolean premium) {
        double tarifaPorDia = 60;
        double multaTotal= diasRetraso * tarifaPorDia;

        if (premium) {
            multaTotal = multaTotal * 0.5;
            return multaTotal;
        } else {
            return multaTotal;
        }
    }
}