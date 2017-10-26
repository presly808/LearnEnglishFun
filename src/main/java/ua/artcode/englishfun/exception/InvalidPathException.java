package ua.artcode.englishfun.exception;

/**
 * Created by diversaint on 29.06.17.
 */
public class InvalidPathException extends AppException{
    public InvalidPathException(String message) {
        super("Error!" + message);
    }
}
