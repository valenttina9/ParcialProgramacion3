package programacion3.Parcial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import programacion3.Parcial.dto.ErrorResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsuarioNoEncontradoException.class, VehiculoNoEncontradoException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(EcoRideException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(), 404, "Not Found", exception.getMessage()));
    }

    @ExceptionHandler({
            BateriaInsuficienteException.class,
            MetodoPagoNoSoportadoException.class,
            EstadoVehiculoInvalidoException.class,
            EcoRideException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(EcoRideException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", exception.getMessage()));
    }
}
