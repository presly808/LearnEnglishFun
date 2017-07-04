package ua.artcode.englishfun.Utils;

import ua.artcode.englishfun.exception.RegisterException;

/**
 * Created by diversaint on 04.07.17.
 */
public class Utils {
    public static boolean checkEmail(String email) {
        if (email == null || !email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
            return false;
        return true;
    }

    public static boolean checkPass(String pass) {
        if (pass == null || !pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$"))
            return false;
        return true;
    }
}
