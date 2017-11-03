package ua.artcode.englishfun.translators;

import ua.artcode.englishfun.model.Word;
import ua.artcode.englishfun.model.category.Language;

import java.io.UnsupportedEncodingException;

/**
 * Created by serhii on 09.07.17.
 */
public interface TranslateApi {

    public Word translate(String source, Language sourceLang, Language targetLang) throws UnsupportedEncodingException;

}
