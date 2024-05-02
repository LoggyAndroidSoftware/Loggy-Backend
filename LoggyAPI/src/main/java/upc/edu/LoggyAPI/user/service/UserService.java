package upc.edu.LoggyAPI.user.service;

import upc.edu.LoggyAPI.user.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User updateUser(Long id, User user);
    Long getUserIdByUsername(String username);
    void deleteUser(Long id);

}
