package com.scope.socialboardweb.repository;

import com.scope.socialboardweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

