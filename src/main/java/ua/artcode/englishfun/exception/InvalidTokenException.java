package ua.artcode.englishfun.exception;

/**
 * Created by diversaint on 04.07.17.
 */
public class InvalidTokenException extends AppException {
    public InvalidTokenException(String message) {
        super("Error!" + message);
    }
}
