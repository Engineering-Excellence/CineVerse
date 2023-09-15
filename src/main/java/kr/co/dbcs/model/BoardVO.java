package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardVO {

    private int boardNo; // PK
    private String username;
    private Date boardDate;
    private String boardContent;
    private String boardTitle;
    private int boardView;
    private int boardLike;
    private int boardDislike;

}
