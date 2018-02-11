package ua.artcode.englishfun.translators;

import com.google.gson.Gson;
import ua.artcode.englishfun.model.Word;
import ua.artcode.englishfun.model.category.Language;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by serhii on 09.07.17.
 */
public class TranslateGoogleApiImpl implements TranslateApi {

    public static final String GOOGLE_TRANSLATE_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=";

    public Word translate(String sourceWord, Language sourceLang, Language targetLang) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        // todo do only one time
        System.setProperty("http.agent", "Chrome");
        try {
            //
            URL url = new URL(GOOGLE_TRANSLATE_URL + sourceLang.toString() +
                    "&tl=" + targetLang.toString() + "&dt=t&q=" + sourceWord);

            // todo find some lib that can read all content in one line of code
            InputStream is = url.openStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        // todo use List
        ArrayList arrayList = null;
        try {
            // todo get using ServiceLocator
            arrayList = new Gson().fromJson(result.toString("UTF-8"), ArrayList.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return TranslateResponse.toWord(arrayList);
    }
}
