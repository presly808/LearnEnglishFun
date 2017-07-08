package ua.artcode.englishfun.dao;


/**
 * Created by diversaint on 27.06.17.
 */
public interface IDAO<T> {

        boolean create(T t);
        T read(T t);
        T update(T t, T newT);
        T delete(T t);

}
