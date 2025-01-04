package web.chat.pojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Message {
	private Integer message_no;
	private Integer room_no;
	private Integer sender_id;
	private String message;
	private Timestamp sent_time;
	private Integer receiver_id;
	private String sender_token;
	private String receiver_token;
}
