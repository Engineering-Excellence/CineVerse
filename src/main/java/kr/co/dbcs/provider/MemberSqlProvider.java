package kr.co.dbcs.provider;

import kr.co.dbcs.model.MemberVO;
import org.apache.ibatis.jdbc.SQL;

public class MemberSqlProvider {

    public String insertMember(MemberVO memberVO) {
        return new SQL() {{
            INSERT_INTO("MEMBER");
            VALUES("USERNAME, PASSWORD", "#{username}, #{password}");
        }}.toString();
    }

    public String insertAuth(String username) {
        return new SQL() {{
            INSERT_INTO("AUTH");
            VALUES("USERNAME", "#{username}");
        }}.toString();
    }

    public String selectMemberByUsername(String username) {
        return new SQL() {{
            SELECT("MEMBER.*");
            FROM("MEMBER");
            LEFT_OUTER_JOIN("AUTH ON MEMBER.USERNAME = AUTH.USERNAME");
            WHERE("MEMBER.USERNAME = #{username}");
        }}.toString();
    }
}
