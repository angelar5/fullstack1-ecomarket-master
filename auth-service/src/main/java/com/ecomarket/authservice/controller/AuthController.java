package com.ecomarket.authservice.controller;
import com.ecomarket.authservice.entity.RolePermission;
import com.ecomarket.authservice.repository.RolePermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor @Slf4j
public class AuthController {
    private final RolePermissionRepository repository;
    @GetMapping("/permissions/{roleName}")
    public ResponseEntity<List<String>> getPermissionsByRole(@PathVariable String roleName) {
        log.info("Consultando permisos para el rol: {}", roleName);
        List<String> perms = repository.findByRoleName(roleName).stream()
                .map(RolePermission::getPermissionKey)
                .collect(Collectors.toList());
        return ResponseEntity.ok(perms);
    }
}