package unlar.programacion3.examenfinal.interfaces;

// multa por fin de semana vale 200$ por dia de retraso
public class MultaFinDeSemana implements CalculoMulta {
    @Override
    public double calcularMulta(int diasRetraso, boolean premium) {
        double tarifaPorDia = 200;
        double multaTotal= diasRetraso * tarifaPorDia;

        if (premium) {
            multaTotal = multaTotal * 0.5;
            return multaTotal;
        } else {
            return multaTotal;
        }
    }
}
    
