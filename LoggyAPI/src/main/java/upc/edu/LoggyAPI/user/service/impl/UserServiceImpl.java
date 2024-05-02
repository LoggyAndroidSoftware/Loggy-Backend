package upc.edu.LoggyAPI.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.user.model.User;
import upc.edu.LoggyAPI.user.repository.UserRepository;
import upc.edu.LoggyAPI.user.service.UserService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;
import upc.edu.LoggyAPI.utils.exception.ValidationException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private List<User> users;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("No users found");
        }
        return users;
    }
    @Override
    public User createUser(User user) {
        validateUser(user);
        existsUserByUsername(user);
        return userRepository.save(user);
    }
    @Override
    public User getUserById(Long id)
    {
        existsUserById(id);
        return userRepository.findById(id).get();
    }
    @Override
    public User updateUser(Long id, User user) {
        existsUserById(id);
        validateUser(user);
        User userToUpdate = userRepository.findById(id).get();
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setDni(user.getDni());
        return userRepository.save(userToUpdate);
    }

    @Override
    public Long getUserIdByUsername(String username) {

        User user = userRepository.findByUsernameIgnoreCase(username);
        if(user == null){
            throw new ResourceNotFoundException("User with username " + username + " not found");
        }
        return user.getId();
    }
    @Override
    public void deleteUser(Long id) {
        existsUserById(id);
        userRepository.deleteById(id);
    }
    private void existsUserByUsername(User user) {
        if (userRepository.existsUserByUsernameIgnoreCase(user.getUsername())){
            throw new ResourceNotFoundException("Username already exists");
        }
    }
    private void existsUserById(Long id) {
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
    }
    private void validateUser(User user) {

        if(user.getName() == null || user.getName().isEmpty()){
            throw new ValidationException("Name is required");
        }
        if(user.getName().length() > 150){
            throw new ValidationException("Name must not exceed 150 characters");
        }
        if(user.getLastName() == null || user.getLastName().isEmpty()){
            throw new ValidationException("Last name is required");
        }
        if(user.getLastName().length() > 150){
            throw new ValidationException("Last name must not exceed 150 characters");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new ValidationException("Email is required");
        }
        if(user.getEmail().length() > 100){
            throw new ValidationException("Email must not exceed 100 characters");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()){
            throw new ValidationException("Username is required");
        }
        if (user.getUsername().length() > 100){
            throw new ValidationException("Username must not exceed 100 characters");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()){
            throw new ValidationException("Password is required");
        }
        if (user.getPassword().length() > 100){
            throw new ValidationException("Password must not exceed 100 characters");
        }
        if(user.getDni() == null || user.getDni().isEmpty()){
            throw new ValidationException("DNI is required");
        }
        if(user.getDni().length() != 8){
            throw new ValidationException("DNI must be 8 characters long");
        }
    }
}
