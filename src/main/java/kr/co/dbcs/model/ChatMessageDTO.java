package kr.co.dbcs.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatMessageDTO {
    private String sender;
    private String contents;
}
