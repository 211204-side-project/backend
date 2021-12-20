package com.scope.socialboardweb.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDto {
    @NotNull
    Boolean success;
    Long id;

    public PostResponseDto(Boolean success) {
        this.success = success;
    }

    public PostResponseDto(Boolean success, Long id) {
        this.success = success;
        this.id = id;
    }
}
