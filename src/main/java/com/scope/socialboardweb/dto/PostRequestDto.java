package com.scope.socialboardweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public class PostRequestDto {
    String title;
    String content;
    String nickname;// or Long id;
    String postImgUrl;
    String postVideoUrl;
}
