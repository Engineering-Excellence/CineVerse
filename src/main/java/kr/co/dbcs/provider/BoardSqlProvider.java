package kr.co.dbcs.provider;

import kr.co.dbcs.model.BoardVO;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BoardSqlProvider {

    public String insertBoard(BoardVO boardVO) {
        return new SQL() {{
            INSERT_INTO("BOARD");
            VALUES("BOARDNO, USERNAME, BOARDDATE, BOARDCONTENT, BOARDTITLE, BOARDVIEW, BOARDLIKE, BOARDDISLIKE",
                    "boardNo_seq.NEXTVAL, #{username}, sysdate, #{boardContent}, #{boardTitle}, 0, 0, 0");
        }}.toString();
    }

    public String selectAllBoard() {
        return new SQL() {{
            SELECT("*");
            FROM("(SELECT A.*, ROWNUM AS RNUM FROM (SELECT * FROM BOARD ORDER BY BOARDNO DESC) A)");
            WHERE("RNUM BETWEEN #{start} AND #{end}");
        }}.toString();
    }

    public String selectAllBoardCount() {
        return new SQL() {{
            SELECT("COUNT(*)");
            FROM("BOARD");
        }}.toString();
    }

    public String selectBoard(int boardNo) {
        return new SQL() {{
            SELECT("*");
            FROM("BOARD");
            WHERE("BOARDNO = #{boardNo}");
        }}.toString();
    }

    public String updateBoard(BoardVO boardVO) {
        return new SQL() {{
            UPDATE("BOARD");
            SET("BOARDCONTENT=#{boardContent}, BOARDTITLE=#{boardTitle}");
            WHERE("BOARDNO = #{boardNo}");
        }}.toString();

    }

    public String deleteBoard(int boardNo) {
        return new SQL() {{
            DELETE_FROM("BOARD");
            WHERE("BOARDNO = #{boardNo}");
        }}.toString();
    }

    public String selectBoardByUsername(String username) {
        return new SQL() {{
            SELECT("*");
            FROM("BOARD");
            WHERE("USERNAME = #{username}");
        }}.toString();
    }

    public String searchBoard(Map<String, Object> map) {
        return new SQL() {{
            SELECT("*");
            FROM("BOARD");
            if (((Integer) map.get("searchType")) == 1)
                WHERE("BOARDTITLE LIKE '%' || #{keyword} || '%'");
            else
                WHERE("USERNAME LIKE '%' || #{keyword} || '%'");
            ORDER_BY("BOARDDATE DESC");
        }}.toString();
    }

    public String updateView(int boardNo) {
        return new SQL() {{
            UPDATE("BOARD");
            SET("BOARDVIEW = BOARDVIEW + 1");
            WHERE("BOARDNO = #{boardNo}");
        }}.toString();
    }
}	
