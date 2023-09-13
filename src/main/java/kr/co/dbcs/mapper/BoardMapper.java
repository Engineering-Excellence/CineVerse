package kr.co.dbcs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import kr.co.dbcs.model.BoardVO;
import kr.co.dbcs.provider.BoardSqlProvider;

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
	
}
