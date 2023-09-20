package kr.co.dbcs.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
@NoArgsConstructor
public class MyReplyVO {
    private int boardNo;
    private Date replyDate;
    private String replyContent;
    private String boardTitle;
}
