package kr.co.dbcs.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dbcs.mapper.NoteMapper;
import kr.co.dbcs.model.NoteVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoteServiceImpl implements NoteService{

	private final NoteMapper noteMapper;

	@Override
	public boolean create(NoteVO noteVO) {
		return noteMapper.insertNote(noteVO) >= 1;
	}

	@Override
	public List<NoteVO> readAll() {
		return null;
	}
	
	@Override
	public List<String> readDialog(String username){
		return noteMapper.selectDialogParter(username);
	}

	@Override
	public List<NoteVO> detailDialog(HashMap<String, String> map){
		return noteMapper.selectDialog(map);
	}
	
	@Override
	public boolean update(NoteVO vo) {
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@Override
	public NoteVO read(Integer id) {
		return null;
	}

	@Override
	public List<NoteVO> read(NoteVO noteVO) {
		// TODO Auto-generated method stub
		return null;
	}


}
