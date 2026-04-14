package com.AquaLifeLine.Backend;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByName(String name) {
        return this.userRepository.findByName(name);
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);

    }
}
