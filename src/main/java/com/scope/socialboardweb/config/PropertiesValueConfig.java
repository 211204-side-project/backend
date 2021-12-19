package com.scope.socialboardweb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
// 배포시 배포 서버에서 config.properties가 위치한 경로로 수정하면 됩니다!
@PropertySource( value = "file:C:/dev/config.properties" )
public class PropertiesValueConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-validity-in-seconds}")
    private String jwtValiditySec;
}
