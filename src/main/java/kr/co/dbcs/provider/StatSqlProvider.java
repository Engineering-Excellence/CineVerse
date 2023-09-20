package kr.co.dbcs.provider;

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
            FROM("(" + new SQL() {{
                SELECT("TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, BIRTHDATE)/12)/10) DATA");
                FROM("MEMBER");
            }} + ")");
            GROUP_BY("DATA");
        }}.toString();
    }

    public String getBoardViewData() {
        return new SQL() {{
            SELECT("TITLE, BOARDVIEW");
            FROM("(" + new SQL() {{
                SELECT("BOARDNO || '(' || SUBSTR(BOARDTITLE, 1, 7) || CASE WHEN LENGTH(BOARDTITLE) > 10 THEN '...' WHEN LENGTH(BOARDTITLE) <= 10 THEN SUBSTR(BOARDTITLE, 8, 10) END || ')' AS TITLE, BOARDVIEW");
                FROM("BOARD");
                ORDER_BY("BOARDVIEW DESC");
            }} + ")");
            WHERE("ROWNUM <= 5");
        }}.toString();
    }

    public String getBoardReplyData() {
        return new SQL() {{
            SELECT("TITLE, REPLYCOUNT");
            FROM("(" + new SQL() {{
                SELECT("BOARDNO, BOARDNO || '(' || SUBSTR(BOARDTITLE, 1, 7) || CASE WHEN LENGTH(BOARDTITLE) > 10 THEN '...' WHEN LENGTH(BOARDTITLE) <= 10 THEN SUBSTR(BOARDTITLE, 8, 10) END || ')' AS TITLE");
                FROM("BOARD) A");
                JOIN("(" + new SQL() {{
                    SELECT("BOARDNO, COUNT(*) REPLYCOUNT");
                    FROM("REPLY");
                    GROUP_BY("BOARDNO");
                    ORDER_BY("REPLYCOUNT DESC");
                }} + ") B ON (A.BOARDNO = B.BOARDNO");
            }} + ")");
            WHERE("ROWNUM <= 5");
        }}.toString();
    }

    public String getBoardLastWeekData() {
        return new SQL() {{
            SELECT("A.writedate, nvl(B.boardCount, 0) boardcount");
            FROM("(" + new SQL() {{
                SELECT("to_char(sysdate-7 + LEVEL,'YYYY-MM-DD') AS writedate");
                FROM("DUAL CONNECT BY LEVEL <= 7) A");
                LEFT_OUTER_JOIN("(" + new SQL() {{
                    SELECT("writeDate, count(*) boardCount");
                    FROM("(" + new SQL() {{
                        SELECT("trunc(boarddate) writeDate, trunc(sysdate) - trunc(boarddate) AS DIFF");
                        FROM("BOARD");
                    }} + ")");
                    WHERE("DIFF <= 7");
                    GROUP_BY("WRITEDATE");
                }} + ") B ON (A.writedate = B.writeDate");

            }} + ")");
            ORDER_BY("A.WRITEDATE");
        }}.toString();
    }

    public String getReplyLastWeekData() {
        return new SQL() {{
            SELECT("A.writedate, nvl(B.REPLYCOUNT, 0) REPLYCOUNT");
            FROM("(" + new SQL() {{
                SELECT("to_char(sysdate-7 + LEVEL,'YYYY-MM-DD') AS writedate");
                FROM("DUAL CONNECT BY LEVEL <= 7) A");
                LEFT_OUTER_JOIN("(" + new SQL() {{
                    SELECT("writeDate, count(*) REPLYCOUNT");
                    FROM("(" + new SQL() {{
                        SELECT("trunc(REPLYDATE) WRITEDATE, trunc(sysdate) - trunc(REPLYDATE) as diff");
                        FROM("reply");
                    }} + ")");
                    WHERE("DIFF <= 7");
                    GROUP_BY("WRITEDATE");
                }} + ") B ON (A.writedate = B.writeDate");
            }} + ")");
            ORDER_BY("A.WRITEDATE");
        }}.toString();
    }
}
