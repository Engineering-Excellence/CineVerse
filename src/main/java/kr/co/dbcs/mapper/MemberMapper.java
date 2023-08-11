package kr.co.dbcs.mapper;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.provider.MemberSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;

@Mapper
public interface MemberMapper {
    @InsertProvider(type = MemberSqlProvider.class, method = "insertMember")
    int insertMember(Map<String, String> map);
    @SelectProvider(type = MemberSqlProvider.class, method = "selectMemberByUsername")
    MemberVO selectMemberByUsername(String username);
}
