package ua.artcode.englishfun.utils;

/**
 * Created by diversaint on 04.07.17.
 */
// todo rename to ValidationUtils
public class ValidationUtils {

    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean checkEmail(String email) {

        if (email == null || !email.matches(EMAIL_PATTERN))
            return false;
        return true;
    }

    public static boolean checkPass(String pass) {
        if (pass == null || !pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$"))
            return false;
        return true;
    }
}
