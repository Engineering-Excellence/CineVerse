package kr.co.dbcs.provider;

import kr.co.dbcs.model.LovedVO;
import kr.co.dbcs.model.MemberVO;
import org.apache.ibatis.jdbc.SQL;

public class LovedSqlProvider {
    public String insertLoved(LovedVO lovedVO) {
        return new SQL() {{
            INSERT_INTO("LOVED");
            VALUES("LOVEDNO, USERNAME, MOVIEID",
                    "LOVEDSEQ.NEXTVAL, #{username}, #{movieId}");
        }}.toString();
    }

    public String deleteByUsernameWithId(LovedVO lovedVO) {
        return new SQL() {{
            DELETE_FROM("LOVED");
            WHERE("USERNAME = #{username} AND MOVIEID = #{movieId}");
        }}.toString();
    }

    public String selectMovieIdByUsername(String username) {
        return new SQL() {{
            SELECT("MOVIEID");
            FROM("LOVED");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }
}
