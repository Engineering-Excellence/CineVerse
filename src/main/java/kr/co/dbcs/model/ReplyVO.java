package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ReplyVO {

    private int replyNo;
    private String username;
    private int boardNo;
    private Date replyDate;
    private String replyContent;
    private String relPath;
}
