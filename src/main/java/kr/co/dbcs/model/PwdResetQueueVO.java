package kr.co.dbcs.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
public class PwdResetQueueVO {

	private String username;
	private String code;
	private Date queueDate;
}
