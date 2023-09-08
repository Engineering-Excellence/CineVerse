package kr.co.dbcs.provider;

import kr.co.dbcs.model.MemberVO;
import org.apache.ibatis.jdbc.SQL;

public class MemberSqlProvider {

    public String insertMember(MemberVO memberVO) {
        return new SQL() {{
            INSERT_INTO("MEMBER");
            VALUES("USERNAME, PASSWORD, MOBILE, EMAIL, GENDER, BIRTHDATE",
                    "#{username}, #{password}, #{mobile}, #{email}, #{gender}, #{birthDate}");
        }}.toString();
    }

    public String insertAuth(String username) {
        return new SQL() {{
            INSERT_INTO("AUTH");
            VALUES("USERNAME, AUTHORITY",
                    "#{username}, 'ROLE_USER'");
        }}.toString();
    }

    public String selectMemberByUsername(String username) {
        return new SQL() {{
            SELECT("*");
            FROM("(" + new SQL() {{
                SELECT("MEMBER.*, AUTHORITY");
                FROM("MEMBER");
                INNER_JOIN("AUTH ON MEMBER.USERNAME = AUTH.USERNAME");
                WHERE("AUTH.USERNAME = #{username}");
            }} + ")");
            WHERE("ROWNUM <= 1");
        }}.toString();
    }

    public String getAuthListByUsername(String username) {
        return new SQL() {{
            SELECT("*");
            FROM("AUTH");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }

    public String selectAllMember() {
        return new SQL() {{
            // 멤버의 정보를 pw빼고 가져오게 하기
            SELECT("*");
            FROM("MEMBER");
        }}.toString();
    }
}
