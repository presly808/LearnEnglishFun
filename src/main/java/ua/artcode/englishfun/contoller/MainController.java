package ua.artcode.englishfun.contoller;

import ua.artcode.englishfun.exception.AppException;
import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.InvalidWordException;
import ua.artcode.englishfun.exception.RegisterException;
import ua.artcode.englishfun.model.Text;
import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.Language;
import ua.artcode.englishfun.model.category.LanguageCategory;

/**
 * Created by serhii on 24.06.17.
 */
public interface MainController {

    // access token, we will use this accessToken to auth our user
    String logIn(String email, String pass) throws InvalidLoginException;

    Controller.GeneralResponse register(String email, String pass, EnglishLvl englishLvl, LanguageCategory[] languageCategories) throws RegisterException;

    String translate(String word, Language languageDest) throws InvalidWordException;

    Controller.GeneralResponse addText(Text text) throws AppException;
}
