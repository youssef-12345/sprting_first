package com.example.supplychain.admin.controller;

import com.example.supplychain.admin.service.AdminService;
import com.example.supplychain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Admin management endpoints - ADMIN only")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Retrieve all users in the system (ADMIN only)")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by ID (ADMIN only)")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/role/admin")
    @Operation(summary = "Get all admin users", description = "Retrieve all users with ADMIN role")
    public ResponseEntity<List<User>> getAdminUsers() {
        List<User> admins = adminService.getAdminUsers();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/users/role/manager")
    @Operation(summary = "Get all manager users", description = "Retrieve all users with MANAGER role")
    public ResponseEntity<List<User>> getManagerUsers() {
        List<User> managers = adminService.getManagerUsers();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/users/role/user")
    @Operation(summary = "Get all regular users", description = "Retrieve all users with USER role")
    public ResponseEntity<List<User>> getRegularUsers() {
        List<User> users = adminService.getRegularUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users/{id}/promote-to-admin")
    @Operation(summary = "Promote user to ADMIN", description = "Promote a user to ADMIN role")
    public ResponseEntity<User> promoteToAdmin(@PathVariable Long id) {
        User user = adminService.promoteToAdmin(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/{id}/promote-to-manager")
    @Operation(summary = "Promote user to MANAGER", description = "Promote a user to MANAGER role")
    public ResponseEntity<User> promoteToManager(@PathVariable Long id) {
        User user = adminService.promoteToManager(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/{id}/demote-to-user")
    @Operation(summary = "Demote user to USER", description = "Demote a user to USER role")
    public ResponseEntity<User> demoteToUser(@PathVariable Long id) {
        User user = adminService.demoteToUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/{id}/deactivate")
    @Operation(summary = "Deactivate user", description = "Deactivate a user account")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        User user = adminService.deactivateUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/{id}/activate")
    @Operation(summary = "Activate user", description = "Activate a deactivated user account")
    public ResponseEntity<User> activateUser(@PathVariable Long id) {
        User user = adminService.activateUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete user", description = "Delete a user from the system")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    @Operation(summary = "Get admin statistics", description = "Retrieve system statistics for admin dashboard")
    public ResponseEntity<Map<String, Object>> getAdminStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", adminService.getTotalUserCount());
        stats.put("activeUsers", adminService.getActiveUserCount());
        stats.put("admins", adminService.getAdminUsers().size());
        stats.put("managers", adminService.getManagerUsers().size());
        stats.put("regularUsers", adminService.getRegularUsers().size());
        return ResponseEntity.ok(stats);
    }
}
