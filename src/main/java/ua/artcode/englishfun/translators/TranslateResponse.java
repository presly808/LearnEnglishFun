package ua.artcode.englishfun.translators;

import ua.artcode.englishfun.model.category.Language;

/**
 * Created by serhii on 09.07.17.
 */
public class TranslateResponse {
    
    private String source;
    private String translation;
    private String translatorInfo;
    
    private Language sourceLang;
    private Language targetLang;

    public TranslateResponse() {
    }

    public TranslateResponse(String source, String translation, String translatorInfo, Language sourceLang, Language targetLang) {
        this.source = source;
        this.translation = translation;
        this.translatorInfo = translatorInfo;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }


    public TranslateResponse setSource(String source) {
        this.source = source;
        return this;
    }

    public TranslateResponse setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public TranslateResponse setTranslatorInfo(String translatorInfo) {
        this.translatorInfo = translatorInfo;
        return this;
    }

    public TranslateResponse setSourceLang(Language sourceLang) {
        this.sourceLang = sourceLang;
        return this;
    }

    public TranslateResponse setTargetLang(Language targetLang) {
        this.targetLang = targetLang;
        return this;
    }

    @Override
    public String toString() {
        return "TranslateResponse{" +
                "source='" + source + '\'' +
                ", translation='" + translation + '\'' +
                ", translatorInfo='" + translatorInfo + '\'' +
                ", sourceLang=" + sourceLang +
                ", targetLang=" + targetLang +
                '}';
    }
}
