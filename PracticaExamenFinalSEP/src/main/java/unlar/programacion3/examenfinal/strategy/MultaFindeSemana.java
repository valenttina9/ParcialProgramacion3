package unlar.programacion3.examenfinal.strategy;

public class MultaFindeSemana implements MultaStrategy {
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso + 200.0;
    }
    
}
