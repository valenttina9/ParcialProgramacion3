package unlar.programacion3.examenfinal.strategy;

public class MultaCampania implements MultaStrategy {
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * 60.0;
    }
    
}
