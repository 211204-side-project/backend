package com.scope.socialboardweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String commnet;
    private Long userId;

    public CommentResponseDto(Long commentId, Long userId, String comment) {
        this.commentId = commentId;
        this.userId = userId;
        this.commnet = comment;
    }
}
