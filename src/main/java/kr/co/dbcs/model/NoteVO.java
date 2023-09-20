package kr.co.dbcs.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
@NoArgsConstructor
public class NoteVO {
    private int noteNo;
    private String noteWriter; //발신자
    private String noteListener; //수신자
    private String content;
    private Date noteDate;
}
