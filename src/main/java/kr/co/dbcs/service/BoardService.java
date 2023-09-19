package kr.co.dbcs.service;

import kr.co.dbcs.model.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardService extends CRUDService<BoardVO, Integer> {

    List<BoardVO> readByUsername(String username);

    boolean updateView(Integer boardNo);

    List<BoardVO> search(Map<String, Object> map);

    List<BoardVO> readAll(Map<String, Integer> map);

    int count();

    List<BoardVO> readAllNotice();
}
