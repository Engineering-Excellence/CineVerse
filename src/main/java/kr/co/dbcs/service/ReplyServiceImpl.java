package kr.co.dbcs.service;

import kr.co.dbcs.mapper.ReplyMapper;
import kr.co.dbcs.model.MyReplyVO;
import kr.co.dbcs.model.ReplyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    @Override
    public boolean create(ReplyVO replyVO) {
        return replyMapper.insertReply(replyVO) >= 1;
    }

    @Override
    public boolean delete(Integer replyNo) {

        return replyMapper.deleteReply(replyNo) >= 1;
    }

    @Override
    public boolean update(ReplyVO replyVO) {
        return false;
    }

    @Override
    public ReplyVO read(Integer id) {
        return null;
    }


    @Override
    public List<ReplyVO> readAllByBoardNo(int boardNo) {
        return replyMapper.selectAllReply(boardNo);
    }

    @Override
    public List<MyReplyVO> readByUsername(String username) {
        return replyMapper.readByUsername(username);
    }
}
