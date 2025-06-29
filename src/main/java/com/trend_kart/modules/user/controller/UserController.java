package com.trend_kart.modules.user.controller;

import com.trend_kart.modules.user.dto.UserDTO;
import com.trend_kart.modules.user.service.implementation.UserServiceImpl;
import com.trend_kart.utils.CommonUtils;
import com.trend_kart.utils.Update;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.trend_kart.constants.TrendKartConstants.CREATE;
import static com.trend_kart.constants.TrendKartConstants.UPDATE;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final CommonUtils commonUtils;

    UserController(final UserServiceImpl userService, final CommonUtils commonUtils) {
        this.userService = userService;
        this.commonUtils = commonUtils;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return commonUtils.generateResponse(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") UUID id) {
        return commonUtils.generateResponse(userService.findUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO user) {
        return commonUtils.generateResponse(userService.saveUser(null, user, CREATE));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") UUID id, @Validated(Update.class) @RequestBody UserDTO user) {
        return commonUtils.generateResponse(userService.saveUser(id, user, UPDATE));
    }
}
