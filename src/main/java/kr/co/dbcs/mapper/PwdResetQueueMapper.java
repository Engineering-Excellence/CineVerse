package kr.co.dbcs.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import kr.co.dbcs.model.PwdResetQueueVO;
import kr.co.dbcs.provider.PwdResetQueueProvider;

@Mapper
public interface PwdResetQueueMapper {
	
    @InsertProvider(type = PwdResetQueueProvider.class, method = "insertPwdResetQueue")
    int insertPwdResetQueue(PwdResetQueueVO pwdResetQueueVO);

    @SelectProvider(type = PwdResetQueueProvider.class, method = "selectPwdResetQueue")
    int selectPwdResetQueue(Map<String, String> map);
    
    @DeleteProvider(type= PwdResetQueueProvider.class, method = "deletePwdResetQueue")
    int deletePwdResetQueue(Map<String, String> map);
}
