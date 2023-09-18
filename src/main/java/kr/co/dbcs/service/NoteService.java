package kr.co.dbcs.service;

import java.util.HashMap;
import java.util.List;

import kr.co.dbcs.model.NoteVO;

public interface NoteService extends CRUDService<NoteVO, Integer>{

	List<NoteVO> read(NoteVO noteVO);

	List<String> readDialog(String username);

	List<NoteVO> detailDialog(HashMap<String, String> map);

}
