package ua.artcode.englishfun.utils;

import ua.artcode.englishfun.model.Dictionary;
import ua.artcode.englishfun.model.Word;
import ua.artcode.englishfun.model.category.EnglishLvl;
import ua.artcode.englishfun.model.category.Language;
import ua.artcode.englishfun.model.category.LanguageCategory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by diversaint on 29.06.17.
 */
public class DictUtils {

    // todo do not use absolute paths, get files from resources (https://www.mkyong.com/java/java-read-a-file-from-resources-folder/)
    public static final String dictionaryDB = "database/dict.txt";

    public static Dictionary convertXmlToDict(String name, Path path, LanguageCategory languageCategory, EnglishLvl englishLvl, Language language) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.setNamme(name);
        Map<String, Word> vocabluary = new HashMap<>();
        String content = new String(Files.readAllBytes(path));

/*        File inputFile = new File("/Users/macbook/IdeaProjects/LearnEnglishFun/src/main/resources/untitled.xml");
        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();

        int a = children.getLength();*/

        String[] dictArray = content.split("<ar>");
        Pattern pattern;
        Matcher matcher;

        // todo use stream
        for (int i = 0; i < dictArray.length; i++) {

            pattern = Pattern.compile("<k>(.+?)</k>");
            matcher = pattern.matcher(dictArray[i]);

            if (!matcher.find()) continue;

            // todo add comment
            String englWord = matcher.group(1);

            dictArray[i] = dictArray[i].replaceAll("\n", "");
            pattern = Pattern.compile("(</k>)(.*)(?=</ar>)");
            matcher = pattern.matcher(dictArray[i]);
            if (!matcher.find()) continue;
            // todo add commnet that explains why did you use group(2)
            String translWord = matcher.group(2);

            Word word = new Word.WordBuilder().setEnglish(englWord).setLanguageCategory(languageCategory).setEnglishLvl(englishLvl).build();

            switch (language) {
                case Rus:
                    word.setRussian(translWord);
                    break;
                case Ukr:
                    word.setUkrainian(translWord);
            }
            vocabluary.put(englWord, word);
        }

        dictionary.setVocabluary(vocabluary);
        return dictionary;
    }
}
