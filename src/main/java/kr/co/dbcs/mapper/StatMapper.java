package kr.co.dbcs.mapper;

import kr.co.dbcs.model.ReplyVO;
import kr.co.dbcs.provider.ReplySqlProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface StatMapper {
    @SelectProvider(type = ReplySqlProvider.class, method = "selectReplyByUsername")
    List<ReplyVO> readByUsername(String username);
}
