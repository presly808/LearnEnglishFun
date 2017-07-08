package ua.artcode.englishfun.model;

/**
 * Created by serhii on 24.06.17.
 */
// todo dto (data transfer object) package, and move this class to created package
public class GeneralResponse {

    private final String message;
    private final boolean success;

    public GeneralResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessResponse(){
        return success;
    }

}
