package web.member.pojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Member {

	private Integer member_no;
	private String email;
	private String mpassword;
	private String member_name;
	private String member_nick_name;
	private String member_pic;
	private String phone;
	private String introduction;
	private Integer companion_comment;
	private Integer companion_score;
	private Integer custmer_comment;
	private Integer custmer_score;
	private Timestamp registration_time;
	private Boolean member_status;
	private String member_token;
}
