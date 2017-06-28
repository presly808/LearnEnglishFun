package ua.artcode.englishfun.model;

import ua.artcode.englishfun.model.category.LanguageCategory;

/**
 * Created by serhii on 24.06.17.
 */
public class Word implements Comparable<Word>{
    private String english;
    private String[] ukrainian;
    private String[] russian;
    private LanguageCategory languageCategory;
    private String description;
    private String englishExamplesWithTranslate;

    public Word(WordBuilder wordBuilder) {
        this.english = wordBuilder.english;
        this.ukrainian = wordBuilder.ukrainian;
        this.russian = wordBuilder.russian;
        this.languageCategory = wordBuilder.languageCategory;
        this.description = wordBuilder.description;
        this.englishExamplesWithTranslate = wordBuilder.englishExamplesWithTranslate;
    }

    public static class WordBuilder {

        private String english;
        private String[] ukrainian;
        private String[] russian;
        private LanguageCategory languageCategory;
        private String description;
        private String englishExamplesWithTranslate;
        public WordBuilder(){
        }


        public WordBuilder setEnglish(String english) {
            this.english = english;
            return this;
        }

        public WordBuilder setUkrainian(String[] ukrainian) {
            this.ukrainian = ukrainian;
            return this;
        }

        public WordBuilder setRussian(String[] russian) {
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
