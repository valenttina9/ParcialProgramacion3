package unlar.programacion3.examenfinal.strategy;

public class MultaNormal implements MultaStrategy {
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * 100.0;
    }
}
