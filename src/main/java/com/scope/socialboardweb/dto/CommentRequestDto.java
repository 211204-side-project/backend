package com.scope.socialboardweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public class CommentRequestDto {
    private Long UserId;
    private String comment;
    private Long postId;
}
