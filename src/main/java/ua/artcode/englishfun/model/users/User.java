package ua.artcode.englishfun.model.users;

import ua.artcode.englishfun.exception.InvalidWordException;
import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.LanguageCategory;
import ua.artcode.englishfun.model.Video;
import ua.artcode.englishfun.model.Word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by diversaint on 26.06.17.
 */
public class User implements Comparable<User>{
    private static final int MAX_WORDS_TO_STUDY = 200;
    private int id;
    private final String email;

    private String pass;
    private EnglishLvl englishLvl;
    private List<Word> learnedWords;
    private List<Word> wordsToStudy;
    private List<Video> watchedVideo;
    private List<Video> favouriteVideo;
    private LanguageCategory[] languageCategories;
    private boolean ukr;
    private boolean rus;

    public User(UserBuilder builder) {
        this.email = builder.email;
        this.pass = builder.pass;
        this.englishLvl = builder.englishLvl;
        this.languageCategories = builder.languageCategory;
        learnedWords = new ArrayList<>();
        wordsToStudy = new ArrayList<>();
        watchedVideo = new ArrayList<>();
        favouriteVideo = new ArrayList<>();
    }

    public boolean isUkr() {
        return ukr;
    }

    public void setUkr(boolean ukr) {
        this.ukr = ukr;
    }

    public boolean isRus() {
        return rus;
    }

    public void setRus(boolean rus) {
        this.rus = rus;
    }

    public void setEnglishLvl(EnglishLvl englishLvl) {
        this.englishLvl = englishLvl;
    }

    public void setPass(String pass) {

        this.pass = pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public EnglishLvl getEnglishLvl() {
        return englishLvl;
    }

    public Collection<Word> getLearnedWords() {
        return learnedWords;
    }

    public Collection<Word> getWordsToStudy() {
        return wordsToStudy;
    }

    public List<Video> getWatchedVideo() {
        return watchedVideo;
    }

    public List<Video> getFavouriteVideo() {
        return favouriteVideo;
    }

    public LanguageCategory[] getLanguageCategories() {
        return languageCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        return pass.equals(user.pass);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + pass.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", englishLvl=").append(englishLvl);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(User o) {
        return this.email.compareTo(o.email);
    }

    public static class UserBuilder {
        private String email;
        private String pass;
        private EnglishLvl englishLvl;
        private LanguageCategory[] languageCategory;

        //public UserBuilder(){
        //}

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPass(String pass) {
            this.pass = pass;
            return this;
        }

        public UserBuilder setEnglishLvl(EnglishLvl englishLvl) {
            this.englishLvl = englishLvl;
            return this;
        }

        public UserBuilder setLanguageCategory(LanguageCategory[] languageCategory) {
            this.languageCategory = languageCategory;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public boolean addToWordsToStudy(Word word) throws InvalidWordException {
        if (word == null) throw new InvalidWordException("Incorrect word to added");
        if (wordsToStudy.contains(word)) throw new InvalidWordException(word + " already in list");
        if (wordsToStudy.size() == MAX_WORDS_TO_STUDY) throw new InvalidWordException("Max words in the list. You have 200 words in words to study");
        int index = learnedWords.indexOf(word);
        if (index >= 0){
            word = learnedWords.remove(index);
        }
        return wordsToStudy.add(word);
    }

    public boolean moveWordToLearned(Word word) throws InvalidWordException {
        if (word == null) throw new InvalidWordException("Incorrect word to added");
        if (learnedWords.contains(word)) throw new InvalidWordException(word + " already in list");
        int index = wordsToStudy.indexOf(word);
        if (index >= 0)
            word = wordsToStudy.remove(index);
        return learnedWords.add(word);
    }







}
