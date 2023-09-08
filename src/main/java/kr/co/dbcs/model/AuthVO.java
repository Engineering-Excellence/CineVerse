package kr.co.dbcs.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AuthVO {

    private String username;    // FK
    private String authority;
}
