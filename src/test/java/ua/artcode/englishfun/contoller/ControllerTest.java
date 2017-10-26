package ua.artcode.englishfun.contoller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.englishfun.DAO.UserDAO;
import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.InvalidTokenException;
import ua.artcode.englishfun.exception.InvalidWordException;
import ua.artcode.englishfun.exception.RegisterException;
import ua.artcode.englishfun.model.Dictionary;
import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.Language;
import ua.artcode.englishfun.model.category.LanguageCategory;
import ua.artcode.englishfun.model.users.User;
import ua.artcode.englishfun.utils.DictUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by diversaint on 04.07.17.
 */
public class ControllerTest {
    private Controller controller;
    private Path path;
    private Dictionary dictionary;
    private Dictionary dictionaryUkr;
    private UserDAO userDB;
    private User user1;
    private User user2;
    private ArrayList<User> users;
    private String email1;
    private String pass1;


    @Before
    public void setUp() throws Exception {
        users = new ArrayList<User>();
        email1 = "user1@gmail.com";
        pass1 = "Qwe1234";
        user1 = new User.UserBuilder().setEmail(email1).setPass(pass1).build();
        user2 = new User.UserBuilder().setEmail("user2@gmail.com").setPass("Qwe123").build();
        users.add(user1);
        users.add(user2);
        userDB = new UserDAO(users);

        path = Paths.get(ControllerTest.class.getResource("/test.xml").getPath());
        dictionaryUkr = DictUtils.convertXmlToDict("dict", path, LanguageCategory.SPOKEN, EnglishLvl.BASIC, Language.Ukr);
        controller = new Controller(userDB, dictionaryUkr);
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void test_convert_xml() throws Exception {
        dictionary = DictUtils.convertXmlToDict("dict", path, LanguageCategory.SPOKEN, EnglishLvl.BASIC, Language.Ukr);
        assertNotNull(dictionary);
        assertTrue(dictionary.getVocabluary().size() == 5);
    }

    @Test(expected = IOException.class)
    public void test_convert_xml_nofile() throws Exception {
        dictionary = DictUtils.convertXmlToDict("dict", Paths.get(""), LanguageCategory.SPOKEN, EnglishLvl.BASIC, Language.Ukr);
    }

    @Test
    public void test_register() throws RegisterException {
        assertTrue(controller.register("user3@gmail.com", "Qwe1234", EnglishLvl.BASIC, null).isSuccessResponse());
    }

    @Test(expected = RegisterException.class)
    public void test_register_invalid_pass() throws RegisterException {
        assertTrue(controller.register("user3@gmail.com", "234", EnglishLvl.BASIC, null).isSuccessResponse());
    }

    @Test(expected = RegisterException.class)
    public void test_register_invalid_email() throws RegisterException {
        assertTrue(!controller.register("user3gmail.com", "Qwe1234", EnglishLvl.BASIC, null).isSuccessResponse());
    }

    @Test(expected = RegisterException.class)
    public void test_register_null_email() throws RegisterException {
        assertTrue(!controller.register(null, null, EnglishLvl.BASIC, null).isSuccessResponse());
    }

    @Test
    public void test_logIn() throws InvalidLoginException, InvalidTokenException {
        String token = controller.logIn(email1, pass1);
        assertNotNull(token);
        assertTrue(userDB.isTokenExist(token));
        assertEquals(userDB.getUserbyToken(token), user1);
    }

    @Test(expected = InvalidLoginException.class)
    public void test_login_wrong_email() throws InvalidLoginException {
        assertNull(controller.logIn("user3@gmail.com", pass1));
    }
    @Test(expected = InvalidLoginException.class)
    public void test_login_wrong_pass() throws InvalidLoginException {
        assertNull(controller.logIn(email1, "Qwe12346"));
    }

    @Test
    public void test_translate() throws InvalidWordException {
        assertEquals(controller.translate("zooms", Language.Ukr), "масштабування");
    }

    @Test(expected = InvalidWordException.class)
    public void test_translate_word_null() throws InvalidWordException {
        assertEquals(controller.translate(null, Language.Ukr), "масштабування");
    }

    @Test(expected = InvalidWordException.class)
    public void test_translate_lang_null() throws InvalidWordException {
        assertEquals(controller.translate("zooms", null), "масштабування");
    }

    @Test(expected = InvalidWordException.class)
    public void test_translate_no_engl_word_in_dict() throws InvalidWordException {
        assertEquals(controller.translate("luck", Language.Ukr), "масштабування");
    }

    @Test(expected = InvalidWordException.class)
    public void test_translate_no_russ_word_in_dict() throws InvalidWordException {
        assertEquals(controller.translate("zooms", Language.Rus), "масштабування");
    }
}
