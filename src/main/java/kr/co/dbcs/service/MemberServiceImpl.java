package kr.co.dbcs.service;

import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements CRUDService<MemberVO, String> {

    private final MemberMapper memberMapper;

    @Override
    public boolean create(MemberVO memberVO) {
        return false;
    }

    @Override
    public MemberVO read(String username) {
        return null;
    }

    @Override
    public List<MemberVO> readAll() {
        return null;
    }

    @Override
    public boolean update(MemberVO memberVO) {
        return false;
    }

    @Override
    public boolean delete(String username) {
        return false;
    }
}
