package com.scope.socialboardweb.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRequestDto {
    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String nickname;

    private String userImgUrl;
}
