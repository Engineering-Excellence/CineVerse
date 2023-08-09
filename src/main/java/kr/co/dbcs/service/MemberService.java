package kr.co.dbcs.service;

import kr.co.dbcs.model.MemberVO;

import java.util.Map;

public interface MemberService extends CRUDService<MemberVO, String> {

    boolean insertMember(Map<String, String> map);
}
