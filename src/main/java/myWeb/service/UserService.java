package myWeb.service;


import myWeb.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUser(User user);
    User getUserById(Long id);
    void editUser(User user);
    void updateUser(User user, String[] roles);
}
