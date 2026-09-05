package unlar.programacion3.examenfinal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import unlar.programacion3.examenfinal.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MaterialNoEncontradoException.class, SocioNoEncontradoException.class, PrestamoNoEncontradoException.class})
    public ResponseEntity<ErrorDTO> handleNoEncontrado(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(MaterialNoDisponibleException.class)
    public ResponseEntity<ErrorDTO> handleNoDisponible(MaterialNoDisponibleException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(ex.getMessage()));
    }
}
