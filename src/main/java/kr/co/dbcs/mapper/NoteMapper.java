package kr.co.dbcs.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import kr.co.dbcs.model.NoteVO;
import kr.co.dbcs.provider.NoteSqlProvider;

@Mapper
public interface NoteMapper {
	
	@InsertProvider(type= NoteSqlProvider.class, method = "insertNote")
	int insertNote(NoteVO noteVO);//메모 전송
	
	@SelectProvider(type= NoteSqlProvider.class, method = "selectALL")
 	List<NoteVO> selectALL();//메모 인덱스 순으로 출력
	
	@SelectProvider(type= NoteSqlProvider.class, method = "selectDialog")
	List<NoteVO> selectDialog(HashMap<String, String> map);
	
	@SelectProvider(type= NoteSqlProvider.class, method = "selectDialogParter")
	List<String> selectDialogParter(String username);
	
}
