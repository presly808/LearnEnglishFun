package ua.artcode.englishfun.translators;

import ua.artcode.englishfun.model.Word;
import ua.artcode.englishfun.model.category.Language;

import java.util.ArrayList;

/**
 * Created by serhii on 09.07.17.
 */
public class TranslateResponse {
    public TranslateResponse() {
    }

    public static Word toWord(ArrayList arrayList){
        Word.WordBuilder wordBuilder = new Word.WordBuilder();
        ArrayList innerArr = (ArrayList) ((ArrayList) arrayList.get(0)).get(0);

        if (arrayList.get(2).equals(Language.en.toString())){
            wordBuilder.setEnglish(innerArr.get(1).toString()).setUkrainian(innerArr.get(0).toString());
        }


        return wordBuilder.build();
    }
}
