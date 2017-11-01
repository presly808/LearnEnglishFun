package ua.artcode.englishfun.contoller;

import ua.artcode.englishfun.DAO.UserDAO;
import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.LanguageCategory;
import ua.artcode.englishfun.utils.DictUtils;
import ua.artcode.englishfun.utils.ValidationUtils;
import ua.artcode.englishfun.exception.AppException;
import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.InvalidWordException;
import ua.artcode.englishfun.exception.RegisterException;
import ua.artcode.englishfun.model.Dictionary;
import ua.artcode.englishfun.model.Text;
import ua.artcode.englishfun.model.Word;
import ua.artcode.englishfun.model.category.Language;
import ua.artcode.englishfun.model.users.User;

import java.io.IOException;

import static ua.artcode.englishfun.utils.FileUtils.getFromJson;
import static ua.artcode.englishfun.utils.FileUtils.writeToJson;

/**
 * Created by diversaint on 01.07.17.
 */
public class Controller implements MainController {
    // todo use relative paths
    // todo name Constants considering Java Convention
    private final String pathToDB = "/database/controller.txt";
    //this.getClass().getClassLoader().getResource("database/controller.txt").getPath();

    private UserDAO userDB;
    private Dictionary dictionary;

    public UserDAO getUserDB() {
        return userDB;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public Controller() throws IOException {
        this.userDB = new UserDAO();
        this.dictionary = getFromJson(DictUtils.dictionaryDB, Dictionary.class);
    }

    public Controller(UserDAO userDB) throws IOException {
        this.userDB = userDB;
        this.dictionary = getFromJson(DictUtils.dictionaryDB, Dictionary.class);
    }

    public Controller(UserDAO userDB, Dictionary dictionary) {

        this.userDB = userDB;
        this.dictionary = dictionary;
    }

    @Override
    public String logIn(String email, String pass) throws InvalidLoginException {
        if (!ValidationUtils.checkEmail(email)) throw new InvalidLoginException("Invalid email");
        if (!ValidationUtils.checkPass(pass)) throw new InvalidLoginException("Invalid password");
        return userDB.createToken(new User.UserBuilder().setEmail(email).setPass(pass).build());
    }

    @Override
    public GeneralResponse register(String email, String pass, EnglishLvl englishLvl, LanguageCategory[] languageCategories) throws RegisterException {
        if (!ValidationUtils.checkEmail(email)) throw new RegisterException("Invalid email");
        if (!ValidationUtils.checkPass(pass)) throw new RegisterException("Invalid password");

        User user = new User.UserBuilder().setEmail(email).setPass(pass).setEnglishLvl(englishLvl).setLanguageCategory(languageCategories).build();

        return userDB.create(user) ? new GeneralResponse("User successfully created", true) : new GeneralResponse("Error creating user, user with this email already exists ", false);
    }

    @Override
    public String translate(String wordToTranslate, Language languageDest) throws InvalidWordException {
        if (wordToTranslate == null || languageDest == null) throw new InvalidWordException("Invalid word to translate or language");
        Word word = dictionary.getVocabluary().get(wordToTranslate);
        String error = "No '" + wordToTranslate + "' in dictionary";
        if (word == null) throw new InvalidWordException(error);
        switch (languageDest){
            case ru:
            if (word.getRussian() == null)
                throw new InvalidWordException(error);
            else
                return word.getRussian();
            case uk:
            if (word.getUkrainian() == null)
                throw new InvalidWordException(error);
            else
                return word.getUkrainian();
        }
        return null;
    }


    @Override
    public GeneralResponse addText(Text text) throws AppException {
        return null;
    }

    public void save() throws IOException {
        writeToJson(pathToDB, this);
    }

    public void load() throws IOException {
        Controller loadController = getFromJson(pathToDB, Controller.class);
        this.userDB = loadController.userDB;
    }

    // todo dto (data transfer object) package, and move this class to created package
    public static class GeneralResponse {

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
}
