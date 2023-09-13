package kr.co.dbcs.model;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
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
