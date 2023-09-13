package kr.co.dbcs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import kr.co.dbcs.model.ReplyVO;
import kr.co.dbcs.provider.ReplySqlProvider;

public interface ReplyMapper {
	
	@InsertProvider(type = ReplySqlProvider.class, method = "insertReply")
	int insertReply(ReplyVO replyVO);
	
	@SelectProvider(type = ReplySqlProvider.class, method = "selectAllReply")
	List<ReplyVO> selectAllReply(int boardNo);
	
	@DeleteProvider(type = ReplySqlProvider.class, method = "deleteReply")
	int deleteReply(int replyNo);


}
