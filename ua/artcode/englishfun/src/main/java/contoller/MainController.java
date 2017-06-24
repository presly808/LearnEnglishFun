package contoller;

import ua.artcode.englishfun.exception.AppException;
import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.RegisterException;
import ua.artcode.englishfun.model.GeneralResponse;
import ua.artcode.englishfun.model.Text;

/**
 * Created by serhii on 24.06.17.
 */
public interface MainController {

    // access token, we will use this accessToken to auth our user
    String login(String email, String pass) throws InvalidLoginException;

    GeneralResponse register(String email, String pass) throws RegisterException;

    String translate(String word, String languageSrc, String languageDest);

    GeneralResponse addText(Text text) throws AppException;
}
