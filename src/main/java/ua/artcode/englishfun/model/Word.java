package ua.artcode.englishfun.model;

import ua.artcode.englishfun.exception.InvalidWordException;
import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.LanguageCategory;

/**
 * Created by serhii on 24.06.17.
 */
public class Word implements Comparable<Word>{
    private String english;
    private String ukrainian;
    private String russian;
    private LanguageCategory languageCategory;
    private EnglishLvl englishLvl;
    private String description;
    private String englishExamplesWithTranslate;
    private String urlImage;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setUkrainian(String ukrainian) {
        this.ukrainian = ukrainian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public void setLanguageCategory(LanguageCategory languageCategory) {
        this.languageCategory = languageCategory;
    }

    public void setEnglishLvl(EnglishLvl englishLvl) {
        this.englishLvl = englishLvl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnglishExamplesWithTranslate(String englishExamplesWithTranslate) {
        this.englishExamplesWithTranslate = englishExamplesWithTranslate;
    }

    public Word(WordBuilder wordBuilder) {
        this.english = wordBuilder.english;
        this.ukrainian = wordBuilder.ukrainian;
        this.russian = wordBuilder.russian;
        this.languageCategory = wordBuilder.languageCategory;
        this.description = wordBuilder.description;
        this.englishExamplesWithTranslate = wordBuilder.englishExamplesWithTranslate;
        this.englishLvl = wordBuilder.englishLvl;
    }

    // todo remove builder, then you can just add return this in Word class
    public static class WordBuilder {

        private String english;
        private String ukrainian;
        private String russian;
        private LanguageCategory languageCategory;
        private String description;
        private String englishExamplesWithTranslate;
        public EnglishLvl englishLvl;

        public WordBuilder(){
        }

        public WordBuilder setEnglishLvl(EnglishLvl englishLvl) {
            this.englishLvl = englishLvl;
            return this;
        }


        public WordBuilder setEnglish(String english) {
            this.english = english;
            return this;
        }

        public WordBuilder setUkrainian(String ukrainian) {
            this.ukrainian = ukrainian;
            return this;
        }

        public WordBuilder setRussian(String russian) {
            this.russian = russian;
            return this;
        }

        public WordBuilder setLanguageCategory(LanguageCategory languageCategory) {
            this.languageCategory = languageCategory;
            return this;
        }

        public WordBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public WordBuilder setEnglishExamplesWithTranslate(String englishExamplesWithTranslate) {
            this.englishExamplesWithTranslate = englishExamplesWithTranslate;
            return this;
        }

        public Word build(){
            return new Word(this);
        }
    }


    public String getEnglish() {
        return english;
    }

    public String getUkrainian() {
        return ukrainian;
    }

    public String getRussian() {
        return russian;
    }

    public LanguageCategory getLanguageCategory() {
        return languageCategory;
    }

    public EnglishLvl getEnglishLvl() {
        return englishLvl;
    }

    public String getDescription() {
        return description;
    }

    public String getEnglishExamplesWithTranslate() {
        return englishExamplesWithTranslate;
    }


    public boolean checkTranslation(String word) throws InvalidWordException {
        if (word == null) throw new InvalidWordException("Invalid word");
        if (this.ukrainian.contains(word)) return true;
        if (this.russian.contains(word)) return true;
        return false;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (this.compareTo((Word) obj) == 0) return true;
        return false;
    }

    public int compareTo(Word o) {
        return this.english.compareTo(o.english);
    }



    @Override
    public int hashCode() {
        int result = english != null ? english.hashCode() : 0;
        result = 31 * result + (ukrainian != null ? ukrainian.hashCode() : 0);
        result = 31 * result + (russian != null ? russian.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (englishExamplesWithTranslate != null ? englishExamplesWithTranslate.hashCode() : 0);
        return result;
    }
}
