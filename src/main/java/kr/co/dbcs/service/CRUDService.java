package kr.co.dbcs.service;

import java.util.Collections;
import java.util.List;

public interface CRUDService<T, ID> {
    /*
        제네릭 타입 정의
        T: 전달할 엔티티 타입
        ID: PK 타입과 동일
     */

    boolean create(T vo);

    T read(ID id);

    default List<T> readAll(String username) { //조회
        return Collections.emptyList();
    }

    boolean update(T vo);//수정

    boolean delete(ID id);//삭젠
}
