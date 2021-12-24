package com.scope.socialboardweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String commnet;
    private Long accountId;

    public CommentResponseDto(Long commentId, Long accountId, String comment) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.commnet = comment;
    }
}
