package kr.co.dbcs.mapper;

import kr.co.dbcs.model.MyReplyVO;
import kr.co.dbcs.model.ReplyVO;
import kr.co.dbcs.provider.ReplySqlProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyMapper {

    @InsertProvider(type = ReplySqlProvider.class, method = "insertReply")
    int insertReply(ReplyVO replyVO);

    @SelectProvider(type = ReplySqlProvider.class, method = "selectAllReply")
    List<ReplyVO> selectAllReply(int boardNo);

    @DeleteProvider(type = ReplySqlProvider.class, method = "deleteReply")
    int deleteReply(int replyNo);

    @SelectProvider(type = ReplySqlProvider.class, method = "selectReplyByUsername")
    List<MyReplyVO> readByUsername(String username);
}
