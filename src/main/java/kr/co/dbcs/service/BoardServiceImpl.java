package kr.co.dbcs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dbcs.mapper.BoardMapper;
import kr.co.dbcs.model.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public boolean create(BoardVO boardVO) {
        return boardMapper.insertBoard(boardVO) >= 1;
    }

    @Override
    public List<BoardVO> readAll() {
        return boardMapper.selectAllBoard();
    }

    @Override
    public boolean update(BoardVO boardVO) {
        return boardMapper.updateBoard(boardVO) >= 1;
    }

    @Override
    public BoardVO read(Integer boardNo) {
        return boardMapper.selectBoard(boardNo);
    }

    @Override
    public boolean delete(Integer boardNo) {
        return boardMapper.deleteBoard(boardNo) >= 1;
    }

    @Override
    public List<BoardVO> readByUsername(String username) {
        return boardMapper.readByUsername(username);
    }
    
    @Override
    public List<BoardVO> search(Map<String, Object> map) {
        return boardMapper.searchBoard(map);
    }
    
    @Override
    public boolean updateView(Integer boardNo) {
        return boardMapper.updateView(boardNo) >= 1;
    }
}
