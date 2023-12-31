package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ReplyVO {

    private int replyNo;
    private String username;
    private int boardNo;
    private Date replyDate;
    private String replyContent;
    @Nullable
    private String relPath;
}
