package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.Comment;
import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void saveComment(User user, Post post,String comment){
        Comment commentEntity = new Comment(user,post,comment);
        commentRepository.save(commentEntity);
    }
}
