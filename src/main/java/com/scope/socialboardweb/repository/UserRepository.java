package com.scope.socialboardweb.repository;

import com.scope.socialboardweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByPhoneNumber(String phone);
    Optional<User> findByNickname(String nickname);
}

