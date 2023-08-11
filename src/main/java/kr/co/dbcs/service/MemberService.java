package kr.co.dbcs.service;

import kr.co.dbcs.model.MemberVO;

public interface MemberService extends CRUDService<MemberVO, String> {

    boolean insertMember(MemberVO memberVO);
}
