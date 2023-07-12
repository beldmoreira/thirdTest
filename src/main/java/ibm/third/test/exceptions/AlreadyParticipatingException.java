package ibm.third.test.exceptions;

public class AlreadyParticipatingException extends RuntimeException{
    public AlreadyParticipatingException(String message) {
        super(message);
    }
}
