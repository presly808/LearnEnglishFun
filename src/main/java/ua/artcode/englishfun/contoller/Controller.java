package ua.artcode.englishfun.contoller;

import ua.artcode.englishfun.dao.UserDAO;
import ua.artcode.englishfun.utils.DictUtils;
import ua.artcode.englishfun.utils.Utils;
import ua.artcode.englishfun.exception.AppException;
import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.InvalidWordException;
import ua.artcode.englishfun.exception.RegisterException;
import ua.artcode.englishfun.model.Dictionary;
import ua.artcode.englishfun.model.GeneralResponse;
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
    private static final String pathToDB = "/database/controller.txt";

    private UserDAO userDB;
    private Dictionary dictionary;

    public UserDAO getUserDB() {
        return userDB;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public Controller() {
        this.userDB = new UserDAO();
        this.dictionary = getFromJson(DictUtils.dictionaryDB, Dictionary.class);
    }

    public Controller(UserDAO userDB) {
        this.userDB = userDB;
        this.dictionary = getFromJson(DictUtils.dictionaryDB, Dictionary.class);
    }

    public Controller(UserDAO userDB, Dictionary dictionary) {

        this.userDB = userDB;
        this.dictionary = dictionary;
    }

    @Override
    public String logIn(String email, String pass) throws InvalidLoginException {
        if (!Utils.checkEmail(email)) throw new InvalidLoginException("Invalid email");
        if (!Utils.checkPass(pass)) throw new InvalidLoginException("Invalid password");
        return userDB.createToken(new User.UserBuilder().setEmail(email).setPass(pass).build());
    }

    @Override
    public GeneralResponse register(String email, String pass) throws RegisterException {
        if (!Utils.checkEmail(email)) throw new RegisterException("Invalid email");
        if (!Utils.checkPass(pass)) throw new RegisterException("Invalid password");

        User user = new User.UserBuilder().setEmail(email).setPass(pass).build();

        return userDB.create(user) ? new GeneralResponse("User successfully created", true) : new GeneralResponse("Error creating user", false);
    }

    @Override
    public String translate(String wordToTranslate, Language languageDest) throws InvalidWordException {
        if (wordToTranslate == null || languageDest == null) throw new InvalidWordException("Invalid word to translate or language");
        Word word = dictionary.getVocabluary().get(wordToTranslate);
        String error = "No '" + wordToTranslate + "' in dictionary";
        if (word == null) throw new InvalidWordException(error);
        switch (languageDest){
            case Rus:
            if (word.getRussian() == null)
                throw new InvalidWordException(error);
            else
                return word.getRussian();
            case Ukr:
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

    public void load(){
        Controller loadController = getFromJson(pathToDB, Controller.class);
        this.userDB = loadController.userDB;
    }
}
