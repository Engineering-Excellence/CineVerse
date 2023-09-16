package kr.co.dbcs.provider;

import kr.co.dbcs.model.MemberImgVO;
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

    public String insertMemberImg(String username) {
        return new SQL() {{
            INSERT_INTO("MEMBERIMG");
            VALUES("USERNAME,",
                    "#{username}");
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

    public String updateMemberInfo(MemberVO memberVO) {
        return new SQL() {{
            UPDATE("MEMBER");
            SET("MOBILE = #{mobile}, EMAIL = #{email}");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }

    public String deleteMember(String username) {
        return new SQL() {{
            DELETE_FROM("MEMBER");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }

    public String updatePassword(MemberVO memberVO) {
        return new SQL() {{
            UPDATE("MEMBER");
            SET("PASSWORD = #{password}");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }

    public String getRelPath(String username) {
        return new SQL() {
            {
                SELECT("RELPATH");
                FROM("MEMBERIMG");
                WHERE("USERNAME = #{username}");
            }
        }.toString();
    }

    public String saveImg(MemberImgVO memberImgVO) {
        return new SQL() {{
            UPDATE("MEMBERIMG");
            SET("ABSPATH = #{absPath}, RELPATH = #{relPath}, FILENAME = #{fileName}");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }

    public String deleteImg(String username) {
        return new SQL() {{
            UPDATE("MEMBERIMG");
            SET("ABSPATH = NULL, RELPATH = NULL, FILENAME = NULL");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }
}
