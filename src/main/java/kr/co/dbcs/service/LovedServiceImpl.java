package kr.co.dbcs.service;

import kr.co.dbcs.mapper.LovedMapper;
import kr.co.dbcs.model.LovedVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LovedServiceImpl implements LovedService {

    private final LovedMapper lovedMapper;

    @Override
    public boolean create(LovedVO vo) {
        return lovedMapper.insertLoved(vo) > 0;
    }

    @Override
    public LovedVO read(Integer integer) {
        return null;
    }

    @Override
    public List<LovedVO> readAll() {
        return LovedService.super.readAll();
    }

    @Override
    public boolean update(LovedVO vo) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean deleteByUsernameWithId(LovedVO vo) {
        return lovedMapper.deleteByUsernameWithId(vo) > 0;
    }

    @Override
    public ArrayList<String> getLovedByUsername(String username) {
        return lovedMapper.getLovedByUsername(username);
    }
}
