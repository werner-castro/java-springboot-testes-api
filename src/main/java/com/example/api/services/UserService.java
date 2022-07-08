package com.example.api.services;

import com.example.api.domain.User;
import com.example.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User create(UserDTO obj);

    User update(UserDTO obj);
    User findById(Integer id);

    List<User> findAll();
}
