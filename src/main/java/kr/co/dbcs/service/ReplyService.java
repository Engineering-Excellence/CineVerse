package kr.co.dbcs.service;

import java.util.List;

import kr.co.dbcs.model.ReplyVO;

public interface ReplyService extends CRUDService<ReplyVO, Integer>  {

	List<ReplyVO> readAllByBoardNo(int boardNo);

}
