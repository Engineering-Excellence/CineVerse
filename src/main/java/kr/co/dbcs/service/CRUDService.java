package kr.co.dbcs.service;

import java.util.Collections;
import java.util.List;

import kr.co.dbcs.model.PwdResetQueueVO;

public interface CRUDService<T, ID> {
    /*
        제네릭 타입 정의
        T: 전달할 엔티티 타입
        ID: PK 타입과 동일
     */

    boolean create(T vo);

    T read(ID id);

    default List<T> readAll() {
        return Collections.emptyList();
    }

    boolean update(T vo);

    boolean delete(ID id);


}
