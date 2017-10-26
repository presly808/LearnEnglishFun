package ua.artcode.englishfun.translators;

import ua.artcode.englishfun.model.category.Language;

/**
 * Created by serhii on 09.07.17.
 */
public interface TranslateApi {

    TranslateResponse translate(String source, Language sourceLang, Language targetLang);

}
