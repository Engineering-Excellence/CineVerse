package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AuthVO {

    private String username;    // FK
    private String authority;
}
