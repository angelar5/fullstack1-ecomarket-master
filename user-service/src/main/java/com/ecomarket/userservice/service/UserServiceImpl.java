package com.ecomarket.userservice.service;
import com.ecomarket.userservice.dto.UserDTO;
import com.ecomarket.userservice.entity.User;
import com.ecomarket.userservice.exception.ResourceNotFoundException;
import com.ecomarket.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public List<UserDTO> getAllUsers() {
        log.info("Obteniendo usuarios");
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::toDTO).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID " + id));
    }
    public UserDTO createUser(UserDTO dto) {
        log.info("Creando usuario {}", dto.getEmail());
        User user = User.builder().name(dto.getName()).email(dto.getEmail()).roleName(dto.getRoleName()).build();
        return toDTO(userRepository.save(user));
    }
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
    }
    private UserDTO toDTO(User u) {
        return UserDTO.builder().id(u.getId()).name(u.getName()).email(u.getEmail()).roleName(u.getRoleName()).build();
    }
}