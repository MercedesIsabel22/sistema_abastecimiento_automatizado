package com.isacode.service.impl;


import com.isacode.entity.User;
import com.isacode.repository.UserRepository;
import com.isacode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean validateLogin(String email, String password) {
        List<User> users = userRepository.findByEmailAndPassword(email, password);
        return !users.isEmpty();
    }
}
