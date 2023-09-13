package kr.co.dbcs.provider;

import org.apache.ibatis.jdbc.SQL;

import kr.co.dbcs.model.BoardVO;

public class BoardSqlProvider {
	
	public String insertBoard(BoardVO boardVO) {
		return new SQL() {{
			INSERT_INTO("BOARD");
			VALUES("BOARDNO, USERNAME, BOARDDATE, BOARDCONTENT, BOARDTITLE, BOARDVIEW, BOARDLIKE, BOARDDISLIKE",
					"boardNo_seq.NEXTVAL, #{username}, sysdate, #{boardContent}, #{boardTitle}, 0, 0, 0");
		}}.toString();
	}
	
	public String selectAllBoard(){
		return new SQL() {{
			SELECT("*");
			FROM("BOARD");
			ORDER_BY("BOARDDATE DESC");
			
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
}	
