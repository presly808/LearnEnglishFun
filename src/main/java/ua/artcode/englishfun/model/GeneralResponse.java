package ua.artcode.englishfun.model;

/**
 * Created by serhii on 24.06.17.
 */
public class GeneralResponse {

    private final String message;

    public GeneralResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
