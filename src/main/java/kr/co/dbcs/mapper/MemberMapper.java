package kr.co.dbcs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO MEMBER (username, password) VALUES (#{username}, #{password})")
    int insertMember(Map<String, String> map);
}
