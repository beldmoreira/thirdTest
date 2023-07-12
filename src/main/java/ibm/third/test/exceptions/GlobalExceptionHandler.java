package ibm.third.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(NotFoundException e) {
        return new ResponseEntity<>("Candidato não encontrado", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<String>handleInvalidNameException (InvalidNameException e) {
        return new ResponseEntity<>("Nome inválido", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyParticipatingException.class)
    public ResponseEntity<String>handleAlreadyParticipatingException(AlreadyParticipatingException e) {
        return new ResponseEntity<>("Candidato já participa do processo", HttpStatus.CONFLICT);
    }


}
