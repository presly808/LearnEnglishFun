package ua.artcode.englishfun.db;

import ua.artcode.englishfun.dao.IDAO;
import ua.artcode.englishfun.model.Dictionary;

/**
 * Created by diversaint on 28.06.17.
 */
// todo apparently we need to delete this
public class DictionaryDB implements IDAO<DictionaryDB>{

    private Dictionary dictionary;

    public boolean create(DictionaryDB dictionaryDB) {
        return false;
    }

    @Override
    public DictionaryDB read(DictionaryDB dictionaryDB) {
        return null;
    }

    @Override
    public DictionaryDB update(DictionaryDB dictionaryDB, DictionaryDB newT) {
        return null;
    }

    @Override
    public DictionaryDB delete(DictionaryDB dictionaryDB) {
        return null;
    }
}
