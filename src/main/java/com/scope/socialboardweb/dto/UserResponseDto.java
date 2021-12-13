package com.scope.socialboardweb.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {
    String accessToken;
    String userId;
    String nickname;
    String phoneNumber;

}
