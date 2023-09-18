package kr.co.dbcs.provider;

import kr.co.dbcs.model.ReplyVO;
import org.apache.ibatis.jdbc.SQL;

public class StatSqlProvider {

    public String getMemberCount() {
        return new SQL() {{
            SELECT("COUNT(*)");
            FROM("MEMBER");
        }}.toString();
    }
    public String getBoardCount() {
        return new SQL() {{
            SELECT("COUNT(*)");
            FROM("BOARD");
        }}.toString();
    }
    public String getReplyCount() {
        return new SQL() {{
            SELECT("COUNT(*)");
            FROM("REPLY");
        }}.toString();
    }

    public String getGenderData() {
        return new SQL() {{
            SELECT("CASE WHEN GENDER = 0 THEN '여성' WHEN GENDER = 1 THEN '남성' END AS gender, COUNT(*) AS value");
            FROM("MEMBER");
            GROUP_BY("GENDER");
        }}.toString();
    }

    public String getAgeData() {
        return new SQL() {{
            SELECT("DATA AS age, COUNT(*) AS value");
            FROM("(SELECT TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, BIRTHDATE)/12)/10) DATA FROM MEMBER)");
            GROUP_BY("DATA");
        }}.toString();
    }
}
