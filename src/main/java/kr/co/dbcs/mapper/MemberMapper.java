package kr.co.dbcs.mapper;

import kr.co.dbcs.model.AuthVO;
import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.provider.MemberSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    @InsertProvider(type = MemberSqlProvider.class, method = "insertMember")
    int insertMember(MemberVO memberVO);

    @InsertProvider(type = MemberSqlProvider.class, method = "insertAuth")
    int insertAuth(String username);

    @SelectProvider(type = MemberSqlProvider.class, method = "selectMemberByUsername")
    @Results(id = "memberResultMap", value = {
            @Result(property = "username", column = "username", id = true),
            @Result(property = "password", column = "password"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "birthDate", column = "birthDate"),
            @Result(property = "regDate", column = "regdate"),
            @Result(property = "authList", column = "username", javaType = List.class, many = @Many(select = "getAuthListByUsername"))
    })
    MemberVO selectMemberByUsername(String username);
    @SelectProvider(type = MemberSqlProvider.class, method = "getAuthListByUsername")
    List<AuthVO> getAuthListByUsername(String username);

//    @SelectProvider(type = MemberSqlProvider.class, method = "selectMemberInfo")
//    @Results(id = "memberResultList", value = {
//    		@Result(property = "username", column = "username", id = true),
//    		@Result(property = "mobile", column = "mobile"),
//    		@Result(property = "email", column = "email"),
//    		@Result(property = "gender", column = "gender"),
//    		@Result(property = "birthDate", column = "birthDate"),
//    		@Result(property = "regDate", column = "regDate")
//    })
//    MemberVO selectLoginMem(String username);
    
    @UpdateProvider(type= MemberSqlProvider.class, method = "updateMemberInfo")
    @Results(id ="memberInfoUpdate", value= {
    		@Result(property = "username", column = "username", id = true),
    		@Result(property = "mobile", column="mobile"),
    		@Result(property = "email", column="email")
    })
    int updateMemberInfo(MemberVO memberVO);
    
    @DeleteProvider(type= MemberSqlProvider.class, method = "deleteMemberInfo")
    @Results(id = "memberInfoDelete", value= {
    		@Result(property = "username", column = "username", id=true),
    })
    int deleteMemberInfo (String username);
    
}
