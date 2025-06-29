package com.trend_kart.modules.user.repo;

import com.trend_kart.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    List<UserDTO> findAll();
//    Optional<UserDTO> findById(UUID id);
}
