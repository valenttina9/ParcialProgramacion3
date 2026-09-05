package unlar.programacion3.examenfinal.exceptions;

public class SocioNoEncontradoException extends RuntimeException {
    public SocioNoEncontradoException(int idSocio) {
        super("No se encontró un socio con el id: " + idSocio);
    }
}
