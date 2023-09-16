package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberImgVO {

    private String username;    // 계정, PK & FK
    private String absPath; // 업로드 절대경로
    private String relPath; // 업로드 상대경로
    private String fileName;    // 파일명
}
