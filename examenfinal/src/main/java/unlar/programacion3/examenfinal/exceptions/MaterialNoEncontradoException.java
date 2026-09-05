package unlar.programacion3.examenfinal.exceptions;

public class MaterialNoEncontradoException extends RuntimeException {
    public MaterialNoEncontradoException(String codigoMaterial) {
        super("No se encontró un material con el código: " + codigoMaterial);
    }
}
