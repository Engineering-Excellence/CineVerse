package kr.co.dbcs.mapper;

import kr.co.dbcs.model.BoardVO;
import kr.co.dbcs.provider.BoardSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @InsertProvider(type = BoardSqlProvider.class, method = "insertBoard")
    int insertBoard(BoardVO boardVO);

    @SelectProvider(type = BoardSqlProvider.class, method = "selectAllBoard")
    List<BoardVO> selectAllBoard();

    @SelectProvider(type = BoardSqlProvider.class, method = "selectBoard")
    BoardVO selectBoard(int boardno);

    @UpdateProvider(type = BoardSqlProvider.class, method = "updateBoard")
    int updateBoard(BoardVO boardVO);

    @DeleteProvider(type = BoardSqlProvider.class, method = "deleteBoard")
    int deleteBoard(int boardNo);

    @SelectProvider(type = BoardSqlProvider.class, method = "selectBoardByUsername")
    List<BoardVO> readByUsername(String username);
}
