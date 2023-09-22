package kr.co.dbcs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import kr.co.dbcs.model.AuthVO;
import kr.co.dbcs.model.MemberImgVO;
import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.model.PwdResetQueueVO;
import kr.co.dbcs.provider.MemberSqlProvider;

@Mapper
public interface MemberMapper {

    @InsertProvider(type = MemberSqlProvider.class, method = "insertMember")
    int insertMember(MemberVO memberVO);

    @InsertProvider(type = MemberSqlProvider.class, method = "insertAuth")
    int insertAuth(String username);

    @InsertProvider(type = MemberSqlProvider.class, method = "insertMemberImg")
    int insertMemberImg(String username);

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

    @SelectProvider(type = MemberSqlProvider.class, method = "selectAllMember")
    List<MemberVO> selectAllMember();

    @UpdateProvider(type = MemberSqlProvider.class, method = "updateMemberInfo")
    int updateMemberInfo(MemberVO memberVO);

    @DeleteProvider(type = MemberSqlProvider.class, method = "deleteMember")
    int deleteMember(String username);

    @UpdateProvider(type = MemberSqlProvider.class, method = "updatePassword")
    int updatePassword(MemberVO memberVO);

    @SelectProvider(type = MemberSqlProvider.class, method = "getRelPath")
    String getRelPath(String username);

    @UpdateProvider(type = MemberSqlProvider.class, method = "saveImg")
    int saveImg(MemberImgVO memberImgVO);

    @UpdateProvider(type = MemberSqlProvider.class, method = "deleteImg")
    int deleteImg(String username);

    @SelectProvider(type = MemberSqlProvider.class, method = "getUsernameList")
    List<String> getUsernameList();
    
    @UpdateProvider(type = MemberSqlProvider.class, method = "updatePassword")
    int resetPassword(MemberVO memberVO);
    
    @SelectProvider(type = MemberSqlProvider.class, method = "findPwCheck")
    int findPwCheck(MemberVO memberVO); 
    

}
