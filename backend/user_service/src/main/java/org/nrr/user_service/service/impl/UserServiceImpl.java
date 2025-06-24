package org.nrr.user_service.service.impl;

import org.nrr.user_service.exception.UserNotFoundException;
import org.nrr.user_service.model.User;
import org.nrr.user_service.payload.dto.KeyCloakUserDto;
import org.nrr.user_service.repository.UserRepository;
import org.nrr.user_service.service.KeyCloakService;
import org.nrr.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final KeyCloakService keyCloakService;


    public UserServiceImpl(UserRepository userRepository, KeyCloakService keyCloakService) {
        this.userRepository = userRepository;
        this.keyCloakService = keyCloakService;
    }

    @Override
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        return userRepository.save(user);

    }

    @Override
    public User updateUser(Long id,User user) {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id '"+id+"' not found"));
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            if (!user.getEmail().equals(existingUser.getEmail()) &&
                    userRepository.existsByEmail(user.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            existingUser.setEmail(user.getEmail());
        }

        if (user.getUsername() != null && !user.getUsername().isBlank()) {
            if (!user.getUsername().equals(existingUser.getUsername()) &&
                    userRepository.existsByUsername(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            existingUser.setUsername(user.getUsername());
        }

        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isBlank()) {
            if (!user.getPhoneNumber().equals(existingUser.getPhoneNumber()) &&
                    userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number already exists");
            }
            existingUser.setPhoneNumber(user.getPhoneNumber());
        }

        if (user.getFullName() != null && !user.getFullName().isBlank()) {
            existingUser.setFullName(user.getFullName());
        }

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            //todo hash the password during user sign in
            existingUser.setPassword(user.getPassword());
        }

        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);

    }

    public User getUserById(Long id) {
        Optional<User> opUser= userRepository.findById(id);
        if(opUser.isEmpty())
        {
            throw new UserNotFoundException("User with id '"+id+"' not found");
        }
        return opUser.get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserFromJwt(String jwt) throws Exception {
        KeyCloakUserDto userdto=keyCloakService.fetchUserProfileByJwt(jwt);
        return userRepository.findByEmail(userdto.getEmail());
    }
}
