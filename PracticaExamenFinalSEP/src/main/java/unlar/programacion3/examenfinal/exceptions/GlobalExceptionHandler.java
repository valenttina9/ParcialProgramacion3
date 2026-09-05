package unlar.programacion3.examenfinal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import unlar.programacion3.examenfinal.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SocioNoEncontrado.class)
    public ResponseEntity<ErrorDTO> manejar(SocioNoEncontrado e) {

        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaterialNoEncontrado.class)
    public ResponseEntity<ErrorDTO> manejar(MaterialNoEncontrado e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
