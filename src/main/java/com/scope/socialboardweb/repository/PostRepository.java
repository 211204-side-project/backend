package com.scope.socialboardweb.repository;

import com.scope.socialboardweb.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
