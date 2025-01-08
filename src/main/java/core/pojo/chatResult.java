package core.pojo;

import lombok.Data;

@Data
public class chatResult<T> {
	private boolean statu;
	private String message;
	private Object data;
}
