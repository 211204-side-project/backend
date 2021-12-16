package com.scope.socialboardweb.repository;

import com.scope.socialboardweb.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
