package kr.co.dbcs.service;

import kr.co.dbcs.model.NoteVO;

import java.util.List;
import java.util.Map;

public interface NoteService extends CRUDService<NoteVO, Integer>{

	List<NoteVO> read(NoteVO noteVO);

	List<String> readDialog(String username);

	List<NoteVO> detailDialog(Map<String, String> map);

}
