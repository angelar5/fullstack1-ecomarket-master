package com.ecomarket.userservice.service;
import com.ecomarket.userservice.dto.UserDTO;
import java.util.List;
public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO dto);
    void deleteUser(Long id);
}