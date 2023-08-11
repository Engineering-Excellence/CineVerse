package kr.co.dbcs.service;

import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean insertMember(MemberVO memberVO) {
        memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
        return memberMapper.insertMember(memberVO) >= 1 && memberMapper.insertAuth(memberVO.getUsername()) >= 1;
    }

    @Override
    public boolean create(MemberVO memberVO) {
        return false;
    }

    @Override
    public MemberVO read(String username) {
        return memberMapper.selectMemberByUsername(username);
    }

    @Override
    public List<MemberVO> readAll() {
        return null;
    }

    @Override
    public boolean update(MemberVO MemberVO) {
        return false;
    }

    @Override
    public boolean delete(String username) {
        return false;
    }
}
