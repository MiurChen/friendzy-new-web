package core.pojo;

import lombok.Data;

@Data
public class Result {
	private boolean statu;
	private String message;
	private String token;
	private Object data;
}

