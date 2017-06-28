package ua.artcode.englishfun;

import ua.artcode.englishfun.model.category.LanguageCategory;
import ua.artcode.englishfun.model.users.User;


/**
 * Created by serhii on 24.06.17.
 */
public class Run {
    public static void main(String[] args) {
        User.UserBuilder builder = new User.UserBuilder().setEmail("sdsd@com")
                .setLanguageCategory(new LanguageCategory[]{LanguageCategory.Spoken});
        //builder.build();
        User user = new User(builder);
        int a = 0;
    }
}
