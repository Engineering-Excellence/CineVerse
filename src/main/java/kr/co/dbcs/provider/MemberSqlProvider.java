package kr.co.dbcs.provider;

import kr.co.dbcs.model.MemberVO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.apache.ibatis.annotations.Update;
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
    
    public String selectLoginMem(String username) {
    	return new SQL() {{
    		SELECT("USERNAME, MOBILE, EMAIL, GENDER, BIRTHDATE, REGDATE");
    		FROM ("MEMBER");
    		WHERE("USERNAME = #{username}");
    	}}.toString();
    }
    
    public String updateMemberInfo(MemberVO memberVO) {
    	return new SQL() {{
    		UPDATE("MEMBER");
    		SET("MOBILE = #{mobile} , EMAIL = #{email}");
    		WHERE("USERNAME = #{username}");
    	}}.toString();
    }
    
    public String deleteMemberInfo(String username) {
    	return new SQL() {{
    		DELETE_FROM("MEMBER");
    		WHERE("USERNAME = #{username}");
    	}}.toString();
    }
    
}
