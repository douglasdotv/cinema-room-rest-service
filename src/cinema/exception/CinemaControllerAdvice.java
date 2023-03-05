package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class CinemaControllerAdvice {

    @ExceptionHandler(UnavailableSeatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleUnavailableSeatException(UnavailableSeatException ex) {
        return Map.of("error", ex.getMessage());
    }

}