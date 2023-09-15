package kr.co.dbcs.service;

import kr.co.dbcs.model.ReplyVO;

import java.util.List;

public interface ReplyService extends CRUDService<ReplyVO, Integer> {

    List<ReplyVO> readAllByBoardNo(int boardNo);
}
