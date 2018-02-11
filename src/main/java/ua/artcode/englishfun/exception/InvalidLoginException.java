package ua.artcode.englishfun.exception;

/**
 * Created by serhii on 24.06.17.
 */
public class InvalidLoginException extends AppException {
    public InvalidLoginException(String message) {
        super("Error!" + message);
    }
}
