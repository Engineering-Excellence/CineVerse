package kr.co.dbcs.service;

import kr.co.dbcs.model.BoardVO;

import java.util.List;

public interface BoardService extends CRUDService<BoardVO, Integer> {
    List<BoardVO> readByUsername(String username);
}
