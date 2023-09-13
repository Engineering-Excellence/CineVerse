package kr.co.dbcs.service;

import kr.co.dbcs.model.LovedVO;

import java.util.ArrayList;

public interface LovedService extends CRUDService<LovedVO, Integer>{
    public boolean deleteByUsernameWithId(LovedVO vo);
    public ArrayList<String> getLovedByUsername(String username);
}
