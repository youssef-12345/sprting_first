package com.example.supplychain.admin.service;

import com.example.supplychain.user.entity.User;
import com.example.supplychain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User promoteToAdmin(Long userId) {
        User user = getUserById(userId);
        user.setRole(User.Role.ADMIN);
        return userRepository.save(user);
    }

    public User promoteToManager(Long userId) {
        User user = getUserById(userId);
        user.setRole(User.Role.MANAGER);
        return userRepository.save(user);
    }

    public User demoteToUser(Long userId) {
        User user = getUserById(userId);
        user.setRole(User.Role.USER);
        return userRepository.save(user);
    }

    public User deactivateUser(Long userId) {
        User user = getUserById(userId);
        user.setActive(false);
        return userRepository.save(user);
    }

    public User activateUser(Long userId) {
        User user = getUserById(userId);
        user.setActive(true);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    public List<User> getUsersByRole(User.Role role) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == role)
                .collect(Collectors.toList());
    }

    public List<User> getAdminUsers() {
        return getUsersByRole(User.Role.ADMIN);
    }

    public List<User> getManagerUsers() {
        return getUsersByRole(User.Role.MANAGER);
    }

    public List<User> getRegularUsers() {
        return getUsersByRole(User.Role.USER);
    }

    public long getTotalUserCount() {
        return userRepository.count();
    }

    public long getActiveUserCount() {
        return userRepository.findAll().stream()
                .filter(User::getActive)
                .count();
    }
}
