package com.trend_kart.modules.user.service;

import com.trend_kart.modules.user.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO findUser(UUID id);
    UserDTO saveUser(UUID id, UserDTO user, String mode);
}
