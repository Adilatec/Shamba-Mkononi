package tz.go.ega.shambamkononibackend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "tz.go.ega")
public class ApplicationProperties {

    private String secretkey;
    private String jwtkey;
    private String userpassword;
    private String backend_url;
    private String frontend_url;
}
