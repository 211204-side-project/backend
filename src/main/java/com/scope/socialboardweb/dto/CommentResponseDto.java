package com.scope.socialboardweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String comment;
    private Long userId;

    public CommentResponseDto(Long commentId, Long userId, String comment) {
        this.commentId = commentId;
        this.userId = userId;
        this.comment = comment;
    }
}
