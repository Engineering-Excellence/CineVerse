package kr.co.dbcs.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class MemberSqlProvider {

    public String insertMember(Map<String, String> map) {

        return new SQL() {{
            INSERT_INTO("MEMBER");
            VALUES("username, password", "#{username}, #{password}");
        }}.toString();
    }
}
