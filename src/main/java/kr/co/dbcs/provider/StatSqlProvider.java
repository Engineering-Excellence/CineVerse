package kr.co.dbcs.provider;

import kr.co.dbcs.model.ReplyVO;
import org.apache.ibatis.jdbc.SQL;

public class StatSqlProvider {

    public String selectReplyByUsername(String username) {
        return new SQL() {{
            SELECT("*");
            FROM("REPLY");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }
}
