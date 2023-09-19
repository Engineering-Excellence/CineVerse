package kr.co.dbcs.mapper;

import kr.co.dbcs.model.ReplyVO;
import kr.co.dbcs.provider.ReplySqlProvider;
import kr.co.dbcs.provider.StatSqlProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface StatMapper {
    @SelectProvider(type = StatSqlProvider.class, method = "getMemberCount")
    int getMemberCount();
    @SelectProvider(type = StatSqlProvider.class, method = "getBoardCount")
    int getBoardCount();
    @SelectProvider(type = StatSqlProvider.class, method = "getReplyCount")
    int getReplyCount();
    @SelectProvider(type = StatSqlProvider.class, method = "getGenderData")
    List<Map<String, BigDecimal>> getGenderData();
    @SelectProvider(type = StatSqlProvider.class, method = "getAgeData")
    List<Map<String, BigDecimal>> getAgeData();

    @SelectProvider(type = StatSqlProvider.class, method = "getBoardViewData")
    List<Map<String, Object>> getBoardViewData();
    @SelectProvider(type = StatSqlProvider.class, method = "getBoardReplyData")
    List<Map<String, Object>> getBoardReplyData();
    @SelectProvider(type = StatSqlProvider.class, method = "getBoardLastWeekData")
    List<Map<String, Object>> getBoardLastWeekData();
    @SelectProvider(type = StatSqlProvider.class, method = "getReplyLastWeekData")
    List<Map<String, Object>> getReplyLastWeekData();
}
