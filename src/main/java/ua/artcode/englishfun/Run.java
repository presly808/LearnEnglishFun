package ua.artcode.englishfun;

import ua.artcode.englishfun.Utils.DictUtils;
import ua.artcode.englishfun.Utils.FileUtils;
import ua.artcode.englishfun.model.Dictionary;
import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.Language;
import ua.artcode.englishfun.model.category.LanguageCategory;
import ua.artcode.englishfun.model.users.User;

import java.nio.file.Paths;


/**
 * Created by serhii on 24.06.17.
 */
public class Run {
    public static void main(String[] args) throws Exception {
        User.UserBuilder builder = new User.UserBuilder().setEmail("sdsd@com")
                .setLanguageCategory(new LanguageCategory[]{LanguageCategory.Spoken});
        //builder.build();
        User user = new User(builder);
        int a = 0;
        Dictionary dictionary = new Dictionary();
        dictionary = DictUtils.convertXmlToDict("Dict", Paths.get("./src/main/resources/untitled.xml"),
                LanguageCategory.Spoken, EnglishLvl.Basic, Language.Ukr);
        FileUtils.writeToJson("./src/main/resources/dict.txt", dictionary);
    }
}
