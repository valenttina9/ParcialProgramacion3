package unlar.programacion3.examenfinal.exceptions;

public class MaterialNoDisponibleException extends RuntimeException {
    public MaterialNoDisponibleException(String codigoMaterial) {
        super("El material con código " + codigoMaterial + " no está disponible para préstamo");
    }
}
