package kr.co.dbcs.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import kr.co.dbcs.model.PwdResetQueueVO;

public class PwdResetQueueProvider {
	
    
    public String insertPwdResetQueue(PwdResetQueueVO pwdResetQueueVO) {
    	return new SQL() {{
    		INSERT_INTO("PWDRESETQUEUE");
    		VALUES("USERNAME, CODE, QUEUEDATE", "#{username}, #{code}, sysdate");
    	}}.toString();
    }
    
    public String selectPwdResetQueue(Map<String, String> map) {
    	return new SQL() {{
    		SELECT("COUNT(*)");
    		FROM("PWDRESETQUEUE");
    		WHERE("USERNAME = #{username} and CODE = #{code}");
    	}}.toString();
    }
    
    public String deletePwdResetQueue(Map<String, String> map) {
    	return new SQL() {{
    		DELETE_FROM("PWDRESETQUEUE");
    		WHERE("USERNAME = #{username} and CODE = #{code}");
    	}}.toString();
    }

}
