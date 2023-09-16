package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AuthVO {

    private String username;    // FK
    private String authority;
}
