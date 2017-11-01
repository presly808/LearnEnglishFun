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

    public TranslateResponse translate(String sourceWord, Language sourceLang, Language targetLang) throws UnsupportedEncodingException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setProperty("http.agent", "Chrome");
        try {
            URL url = new URL("https://translate.googleapis.com/translate_a/single?client=gtx&sl=" + sourceLang.toString() +
                    "&tl=" + targetLang.toString() + "&dt=t&q=" + sourceWord);
            InputStream is = url.openStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        ArrayList arrayList = new Gson().fromJson(result.toString("UTF-8"), ArrayList.class);
        Word word = TranslateResponse.toWord(arrayList);


        return new Gson().fromJson(result.toString("UTF-8"), TranslateResponse[].class)[0];
    }
}
