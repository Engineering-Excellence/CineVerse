package kr.co.dbcs.provider;

import org.apache.ibatis.jdbc.SQL;

import kr.co.dbcs.model.ReplyVO;

public class ReplySqlProvider {

	public String insertReply(ReplyVO replyVO) {
		return new SQL() {{
			INSERT_INTO("REPLY");
			VALUES("REPLYNO, USERNAME, BOARDNO, REPLYDATE, REPLYCONTENT",
					"replyNo_seq.NEXTVAL, #{username}, #{boardNo}, sysdate, #{replyContent}");
		}}.toString();
	}
	
	public String selectAllReply(int boardNo) {
		return new SQL() {{
			SELECT("*");
			FROM("REPLY");
			WHERE("BOARDNO = #{boardNo}");
		}}.toString();
	}
	
	public String deleteReply(int replyNo) {
		return new SQL() {{
			DELETE_FROM("REPLY");
			WHERE("REPLYNO = #{replyNo}");
		}}.toString();
	}
	
}
