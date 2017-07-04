package ua.artcode.englishfun.DAO;

import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.InvalidTokenException;
import ua.artcode.englishfun.model.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * Created by diversaint on 30.06.17.
 */
public class UserDAO implements IDAO<User> {
    private List<User> users = new ArrayList<>();
    private WeakHashMap <String, User> tokens = new WeakHashMap<>();

    public UserDAO(List<User> users) {
        this.users = users;
    }

    public UserDAO() {
    }

    @Override
    public boolean create(User user) {
        if(users.contains(user)) return false;
        return users.add(user);
    }

    @Override
    public User read(User user) {
        int userIdx = users.indexOf(user);
        return userIdx == -1? null : users.get(userIdx);
    }

    @Override
    public User update(User user, User newUser) {
        int userIdx = users.indexOf(user);
        return userIdx == -1? null : users.set(userIdx, newUser);
    }

    @Override
    public User delete(User user) {
        users.remove(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean isTokenExist(String token) throws InvalidTokenException {
        if(token == null) throw new InvalidTokenException("Token is invalid");
        return tokens.get(token) == null ? false : true;
    }

    public String createToken(User user) throws InvalidLoginException {
        int userIndex = users.indexOf(user);
        if (userIndex == -1) throw new InvalidLoginException("Invalid email or password");
        String token;
        do
        token = UUID.randomUUID().toString();
        while (tokens.containsKey(token));
        tokens.put(token, users.get(userIndex));
        return token;
    }

    public User getUserbyToken(String token) throws InvalidTokenException {
        isTokenExist(token);
        User user = tokens.get(token);
        if (user == null) throw new InvalidTokenException("Invalid token");
        return user;
    }
}
