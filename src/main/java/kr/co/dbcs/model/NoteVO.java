package kr.co.dbcs.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

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
