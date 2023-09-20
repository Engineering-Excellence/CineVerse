package kr.co.dbcs.service;

import kr.co.dbcs.model.MyReplyVO;
import kr.co.dbcs.model.ReplyVO;

import java.util.List;
import java.util.Map;

public interface ReplyService extends CRUDService<ReplyVO, Integer> {

    List<ReplyVO> readAllByBoardNo(int boardNo);

    List<MyReplyVO> readByUsername(String username);
}
