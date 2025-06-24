package org.nrr.user_service.controller;

import jakarta.validation.Valid;
import org.nrr.user_service.exception.UserNotFoundException;
import org.nrr.user_service.mapper.UserMapper;
import org.nrr.user_service.model.User;
import org.nrr.user_service.payload.dto.UserDto;
import org.nrr.user_service.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping("/api/users/profile")
    public ResponseEntity<UserDto> getUserFromJwtToken(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwt(jwt);
        UserDto userDTO=userMapper.mapToDTO(user);


        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long userId
    ) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        if(user==null) {
            throw new UserNotFoundException("User not found");
        }
        UserDto userDto= userMapper.mapToDTO(user);

        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(
    ) throws UserNotFoundException {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
