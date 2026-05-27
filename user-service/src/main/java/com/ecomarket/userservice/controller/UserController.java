package com.ecomarket.userservice.controller;
import com.ecomarket.userservice.dto.UserDTO;
import com.ecomarket.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/users") @RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping public ResponseEntity<List<UserDTO>> getAll() { return ResponseEntity.ok(userService.getAllUsers()); }
    @GetMapping("/{id}") public ResponseEntity<UserDTO> getById(@PathVariable Long id) { return ResponseEntity.ok(userService.getUserById(id)); }
    @PostMapping public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) { return ResponseEntity.status(211).body(userService.createUser(dto)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { userService.deleteUser(id); return ResponseEntity.noContent().build(); }
}