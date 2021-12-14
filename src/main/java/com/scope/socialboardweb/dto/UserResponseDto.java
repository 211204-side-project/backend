package com.scope.socialboardweb.dto;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
public class UserResponseDto {
    private String createAccountMessage;
    String accessToken;
    String userId;
    String nickname;
    String phoneNumber;

}
