package web.customer.bean;

import lombok.Data;

@Data
public class BlackList {
	private Integer user_id;
	private Integer blacklist_id;
	private String blacklist_reason;
}
