package ua.artcode.englishfun.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by diversaint on 27.06.17.
 */
public class Dictionary {
    private String namme;
    private Map<String, Word> vocabluary;

    public Dictionary(String namme, Map<String, Word> vocabluary) {
        this.namme = namme;
        this.vocabluary = vocabluary;
    }

    public Dictionary() {
        this.vocabluary = new HashMap<>();
    }

    public String getNamme() {
        return namme;
    }

    public void setNamme(String namme) {
        this.namme = namme;
    }

    public Map<String, Word> getVocabluary() {
        return vocabluary;
    }

    public void setVocabluary(Map<String, Word> vocabluary) {
        this.vocabluary = vocabluary;
    }
}
