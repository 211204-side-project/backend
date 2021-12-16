package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.CommentRequestDto;
import com.scope.socialboardweb.dto.ResponseDto;
import com.scope.socialboardweb.service.CommentService;
import com.scope.socialboardweb.service.PostService;
import com.scope.socialboardweb.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;



    @PostMapping("/api/comment")
    public ResponseDto saveComment(@RequestBody CommentRequestDto requestDto ){

        User user = userService.findUserById(requestDto.getUserId());
        Post post = postService.findPostBtPostId(requestDto.getPostId());
        commentService.saveComment(user,post,requestDto.getComment());
        return new ResponseDto(true,"댓글이 작성되었습니다");
    }

    @DeleteMapping("/api/comment")
    public ResponseDto deleteComment(@RequestParam Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseDto(true,"댓글이 삭제되었습니다.");
    }

    
}
