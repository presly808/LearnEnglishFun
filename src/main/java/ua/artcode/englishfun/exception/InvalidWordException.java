package ua.artcode.englishfun.exception;

/**
 * Created by diversaint on 30.06.17.
 */
public class InvalidWordException extends Exception {
    public InvalidWordException(String message) {
        super("Error!" + message);
    }
}
