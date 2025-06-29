package com.trend_kart.modules.user.service.implementation;

import com.trend_kart.modules.audit.event.AuditLogEvent;
import com.trend_kart.modules.user.dto.UserDTO;
import com.trend_kart.modules.audit.entity.AuditLog;
import com.trend_kart.modules.user.entity.User;
import com.trend_kart.modules.user.repo.UserRepository;
import com.trend_kart.modules.user.service.UserService;
import com.trend_kart.utils.CommonUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CommonUtils commonUtils;
    private final ApplicationEventPublisher applicationEventPublisher;

    UserServiceImpl(final UserRepository userRepository, final CommonUtils commonUtils,
                    final ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.commonUtils = commonUtils;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Cacheable(key = "all_users", value = "usersCache")
    public List<UserDTO> getAllUsers() {
        log.info("Users::Fetching from DB"); // Added for Testing
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userToUserDTO).toList();
    }

    @Override
    @Cacheable(key = "#id", value = "user_id")
    public UserDTO findUser(UUID id) {
        log.info("UserID::Fetching from DB"); // Added for Testing
        return userToUserDTO(
                Objects.requireNonNull(userRepository.findById(id).orElse(null))
        );
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "'all_users'", value = "'usersCache'"),
            @CacheEvict(key = "#id", value = "user_id")
    })
    public UserDTO saveUser(UUID id, UserDTO user, String mode) {
        User exitingUser = null;

        if (user.getId() != null) {
            exitingUser = userRepository.findById(user.getId()).orElse(null);
        }
        User savedUser = userRepository.save(userDTOTouser(user));
        AuditLog auditLog = AuditLog.builder()
                .action(mode)
                .userId("13123131")
                .userName("Current User Name")
                .entityType("User")
                .entityId(savedUser.getId().toString())
                .previousVersion(commonUtils.convertToMap(exitingUser))
                .currentVersion(commonUtils.convertToMap(savedUser))
                .actedAt(LocalDateTime.now())
                .build();
        applicationEventPublisher.publishEvent(new AuditLogEvent(this, auditLog));
        return userToUserDTO(savedUser);
    }

    public User userDTOTouser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }

    public UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
