package unlar.programacion3.examenfinal.exceptions;

public class PrestamoNoEncontradoException extends RuntimeException {
    public PrestamoNoEncontradoException(String codigoMaterial, int idSocio) {
        super("No existe un préstamo activo del material " + codigoMaterial + " para el socio " + idSocio);
    }
}
