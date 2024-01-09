package co.com.user.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String email;
    private String username;
    private List<String> groups;
    private List<String> roles;
}
