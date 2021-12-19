package com.scope.socialboardweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class CommentRequestDto {
    private Long userId;
    private String comment;
    private Long postId;
}
