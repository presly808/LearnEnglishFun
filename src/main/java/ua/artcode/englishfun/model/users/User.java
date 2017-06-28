package ua.artcode.englishfun.model.users;

import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.LanguageCategory;
import ua.artcode.englishfun.model.Video;
import ua.artcode.englishfun.model.Word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by diversaint on 26.06.17.
 */
public class User {
    private static final int MAX_WORDS_TO_STUDY = 200;
    private static int nextID = 0;
    private int id;
    private final String email;

    private String passHash;
    private EnglishLvl englishLvl;
    private List<Word> learnedWords;
    private List<Word> wordsToStudy;
    private List<Video> watchedVideo;
    private List<Video> favouriteVideo;
    private LanguageCategory[] languageCategories;
    private boolean ukr;
    private boolean rus;

    public User(UserBuilder builder) {
        this.id = ++nextID;
        this.email = builder.email;
        this.passHash = builder.passHash;
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

    public void setPassHash(String passHash) {

        this.passHash = passHash;
    }

    public int getId() {

        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassHash() {
        return passHash;
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

        if (id != user.id) return false;
        if (!email.equals(user.email)) return false;
        return passHash.equals(user.passHash);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + passHash.hashCode();
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

    public static class UserBuilder {
        private String email;
        private String passHash;
        private EnglishLvl englishLvl;
        private LanguageCategory[] languageCategory;

        public UserBuilder(){
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassHash(String passHash) {
            this.passHash = passHash;
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

    public boolean addToWordsToStudy(Word word){
        if (word == null) throw new NullPointerException("Incorrect word to added");

        return false;
    }





}
