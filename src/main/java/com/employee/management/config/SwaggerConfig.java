package com.employee.management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        title = "Employee API",
        description = "This API is for Employee info",
        version = "api/v1"),
        servers = {
                @Server(description = "DevEnv", url="localhost:8080"),
                @Server(description = "TestEnv", url="localhost:8080")
                })
public class SwaggerConfig {
}
