package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.Comment;
import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.CommentResponseDto;
import com.scope.socialboardweb.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void saveComment(User user, Post post,String comment){
        Comment commentEntity = new Comment(user,post,comment);
        commentRepository.save(commentEntity);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다"));
        comment.deleteComment();
        commentRepository.delete(comment);
    }

    public List<CommentResponseDto> getCommnetList(Post post){
        List<Comment> commentList = commentRepository.findAllByPost(post);
        List<CommentResponseDto> responseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            responseDtoList.add(new CommentResponseDto(comment.getId(),comment.getUser().getId(),comment.getComment()));
        }
        return responseDtoList;
    }
}
