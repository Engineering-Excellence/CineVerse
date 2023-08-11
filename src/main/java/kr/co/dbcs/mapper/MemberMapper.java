package kr.co.dbcs.mapper;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.provider.MemberSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface MemberMapper {

    @InsertProvider(type = MemberSqlProvider.class, method = "insertMember")
    int insertMember(MemberVO memberVO);

    @InsertProvider(type = MemberSqlProvider.class, method = "insertAuth")
    int insertAuth(String username);

    @SelectProvider(type = MemberSqlProvider.class, method = "selectMemberByUsername")
    MemberVO selectMemberByUsername(String username);
}
