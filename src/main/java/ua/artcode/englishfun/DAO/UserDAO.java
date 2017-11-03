package ua.artcode.englishfun.DAO;

import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.InvalidTokenException;
import ua.artcode.englishfun.model.users.User;

import java.util.*;

/**
 * Created by diversaint on 30.06.17.
 */
public class UserDAO implements IDAO<User> {

    private int nextID = 0;
    private List<User> users = new ArrayList<>();

    private Map<String, Integer> tokens = new WeakHashMap<>();

    public UserDAO(List<User> users) {
        this.users = users;
    }

    public UserDAO() {
    }


    @Override
    public boolean create(User user) {

        if (isUserRegisted(user)) return false;
        //int id = users.size();
        user.setId(++nextID);

        return users.add(user);
    }

    @Override
    public User read(User user) {
        int userIdx = users.indexOf(user);
        return userIdx == -1 ? null : users.get(userIdx);
    }

    @Override
    public User update(User user, User newUser) {
        int userIdx = users.indexOf(user);
        return userIdx == -1 ? null : users.set(userIdx, newUser);
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

        if (token == null) throw new InvalidTokenException("Token is invalid");

        return tokens.get(token) != null;
    }

    public String createToken(User user) throws InvalidLoginException {

        int userIndex = users.indexOf(user);


        if (!isUserRegisted(user)) {
            throw  new InvalidLoginException("Error! User with this email do not exists! Check email or register it.");
        } else if (userIndex == -1) throw new InvalidLoginException("Invalid password");

        String token;

        do {
            token = UUID.randomUUID().toString();
        } while (tokens.containsKey(token));

        tokens.put(token, users.get(userIndex).getId());

        return token;
    }

    public User getUserbyToken(String token) throws InvalidTokenException {

        isTokenExist(token);

        int userID = tokens.get(token);
        if (userID == -1) throw new InvalidTokenException("Invalid token");

        return users.stream().filter(u -> u.getId() == userID).findFirst().orElse(null);
    }

    public boolean isUserRegisted(User user){
        User isEmailRegisted = users.stream().filter(u -> u.getEmail().equals(user.getEmail())).findFirst().orElse(null);
        return isEmailRegisted != null;
    }
}
