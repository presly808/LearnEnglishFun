package ua.artcode.englishfun.DAO;

import ua.artcode.englishfun.model.users.User;

/**
 * Created by diversaint on 27.06.17.
 */
public interface IDAO<T> {

        boolean create(T t);
        T read(T t);
        T update(T t, T newT);
        boolean delete(T t);

}
