package kr.co.dbcs.mapper;

import kr.co.dbcs.model.LovedVO;
import kr.co.dbcs.provider.LovedSqlProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;

@Mapper
public interface LovedMapper {
    @InsertProvider(type = LovedSqlProvider.class, method = "insertLoved")
    int insertLoved(LovedVO vo);

    @DeleteProvider(type = LovedSqlProvider.class, method = "deleteByUsernameWithId")
    int deleteByUsernameWithId(LovedVO vo);

    @SelectProvider(type = LovedSqlProvider.class, method = "selectMovieIdByUsername")
    ArrayList<String> getLovedByUsername(String username);
}
