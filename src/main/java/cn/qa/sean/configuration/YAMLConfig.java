package cn.qa.sean.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "server")
@Validated
public class YAMLConfig {
    @NotNull
    @NotEmpty
    private Map<String, String> application;
    private Map<String, List<String>> config;
    private Map<String, Credential> users;

    @Data
    public static class Credential {

        private String username;
        private String password;

        // getters and setters

    }
}
