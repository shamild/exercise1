package co.uk.dagsoft.exercise.addressbook.repository.exception;

/**
 * Created by shamil on 04/04/2016.
 */
public class DataException extends RuntimeException {
    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
