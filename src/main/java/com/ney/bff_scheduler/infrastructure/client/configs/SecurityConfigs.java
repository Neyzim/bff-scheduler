package com.ney.bff_scheduler.infrastructure.client.configs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = SecurityConfigs.SECURITY_SCHEME,type = SecuritySchemeType.HTTP,
bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfigs {

    public static final String SECURITY_SCHEME ="bearerAuth";
}
