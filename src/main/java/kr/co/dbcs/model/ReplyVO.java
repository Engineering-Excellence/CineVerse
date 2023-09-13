package kr.co.dbcs.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
public class ReplyVO {
	
	private int replyNo;
	private String username;
	private int boardNo;
	private Date replyDate;
	private String replyContent;
	
}
