package web.companion.pojo;

import lombok.Data;

@Data
public class ComApplicant {//應徵者
	private Integer serviceId;//服務單號	SQL名字:service_id
	private Integer applicant_account ;//應徵者 	SQL名字:applicant_account
	private Integer applyStatus;//應徵的狀態 0:未應徵 1:已應徵 SQL名字:apply_status
	private Integer applicationResult;//應徵結果 0:未得標 1:已得標 SQL名字:application_result 

}
