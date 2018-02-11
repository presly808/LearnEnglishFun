package ua.artcode.englishfun.exception;

/**
 * Created by diversaint on 07.07.17.
 */
public class HttpServerException extends AppException {
    public HttpServerException(String message) {
        super("Error!" + message);
    }
}
