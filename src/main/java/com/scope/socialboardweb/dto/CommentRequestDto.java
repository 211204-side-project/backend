package com.scope.socialboardweb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequestDto {
    private Long userId;
    private String comment;
    private Long postId;
}
