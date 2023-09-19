package kr.co.dbcs.mapper;

import kr.co.dbcs.model.NoteVO;
import kr.co.dbcs.provider.NoteSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoteMapper {
	
	@InsertProvider(type= NoteSqlProvider.class, method = "insertNote")
	int insertNote(NoteVO noteVO);//메모 전송
	
	@SelectProvider(type= NoteSqlProvider.class, method = "selectALL")
 	List<NoteVO> selectALL();//메모 인덱스 순으로 출력
	
	@SelectProvider(type= NoteSqlProvider.class, method = "selectDialog")
	List<NoteVO> selectDialog(Map<String, String> map);
	
	@SelectProvider(type= NoteSqlProvider.class, method = "selectDialogParter")
	List<String> selectDialogParter(String username);
	
}
