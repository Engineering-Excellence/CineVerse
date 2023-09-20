package kr.co.dbcs.mapper;

import kr.co.dbcs.model.BoardVO;
import kr.co.dbcs.provider.BoardSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    @InsertProvider(type = BoardSqlProvider.class, method = "insertBoard")
    int insertBoard(BoardVO boardVO);

    @SelectProvider(type = BoardSqlProvider.class, method = "selectAllBoard")
    List<BoardVO> selectAllBoard(Map<String, Integer> map);

    @SelectProvider(type = BoardSqlProvider.class, method = "selectAllBoardCount")
    int selectAllBoardCount();

    @SelectProvider(type = BoardSqlProvider.class, method = "selectBoard")
    BoardVO selectBoard(int boardno);

    @UpdateProvider(type = BoardSqlProvider.class, method = "updateBoard")
    int updateBoard(BoardVO boardVO);

    @DeleteProvider(type = BoardSqlProvider.class, method = "deleteBoard")
    int deleteBoard(int boardNo);

    @SelectProvider(type = BoardSqlProvider.class, method = "selectBoardByUsername")
    List<BoardVO> readByUsername(String username);

    @SelectProvider(type = BoardSqlProvider.class, method = "searchBoard")
    List<BoardVO> searchBoard(Map<String, Object> map);

    @UpdateProvider(type = BoardSqlProvider.class, method = "updateView")
    int updateView(int boardNo);

    @SelectProvider(type = BoardSqlProvider.class, method = "selectAllNotice")
    List<BoardVO> selectAllNotice();
}

