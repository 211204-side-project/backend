package com.scope.socialboardweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {
    Object object;
    String msg;

    public ResponseDto(Boolean bool,String msg){
        this.object = bool;
        this.msg = msg;
    }
}
