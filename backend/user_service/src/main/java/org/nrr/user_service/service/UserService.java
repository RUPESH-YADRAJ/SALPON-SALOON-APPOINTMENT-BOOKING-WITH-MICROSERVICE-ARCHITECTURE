package org.nrr.user_service.service;

import org.nrr.user_service.model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);
    public User getUserById(Long id);
    public User findUserByEmail(String email);
    public List<User> getAllUsers();

}
