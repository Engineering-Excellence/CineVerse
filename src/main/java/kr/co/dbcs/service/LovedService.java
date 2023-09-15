package kr.co.dbcs.service;

import kr.co.dbcs.model.LovedVO;

import java.util.List;

public interface LovedService extends CRUDService<LovedVO, Integer> {

    boolean deleteByUsernameWithId(LovedVO vo);

    List<String> getLovedByUsername(String username);
}
