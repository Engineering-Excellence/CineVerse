package kr.co.dbcs.provider;

import kr.co.dbcs.model.MemberVO;
import org.apache.ibatis.jdbc.SQL;

public class MemberSqlProvider {

    public String insertMember(MemberVO memberVO) {
        return new SQL() {{
            INSERT_INTO("MEMBER");
            VALUES("USERNAME, PASSWORD, MOBILE, EMAIL, GENDER, BIRTHDATE", "#{username}, #{password}, #{mobile}, #{email}, #{gender}, #{birthDate}");
        }}.toString();
    }

    public String insertAuth(String username) {
        return new SQL() {{
            INSERT_INTO("AUTH");
            VALUES("USERNAME, AUTHORITY", "#{username}, 'ROLE_USER'");
        }}.toString();
    }

    public String selectMemberByUsername(String username) {
        return new SQL() {{
            SELECT("MEMBER.*, AUTH.AUTHORITY");
            FROM("MEMBER");
            LEFT_OUTER_JOIN("AUTH ON MEMBER.USERNAME = AUTH.USERNAME");
            WHERE("MEMBER.USERNAME = #{username}");
        }}.toString();
    }
}
