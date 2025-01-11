package web.companion.pojo;

import java.sql.Timestamp;

import lombok.Data;


//需要1：訂單編號、應徵者ID、應徵者名字、應徵的狀態、應徵結果、刊登標題、開始時間
//需要2：訂單編號、對方名字、對方ID、對方頭像、刊登者名字、刊登者Id、應徵者ID、應徵者名字、應徵的狀態、應徵結果、刊登標題、刊登內容、開始時間、結束時間、服務地區-城市、服務地區-區
@Data
public class ComApplicant {//應徵者
	private Integer orderId;//訂單編號  SQL名字:order_id
	private Integer serviceId;//服務編號	SQL名字:service_id
	private Integer orderStatus;//訂單狀態  SQL名字:order_status
	
	private Integer theirId;//對方ID  SQL名字:theirId
	private String theirName;//對方名字  SQL名字:theirName
	private String theirImg;//對方頭像  SQL名字:theirImg
	private String orderPosterName;//刊登者名字  SQL名字:poster_name
	private Integer orderPoster;//刊登者Id  SQL名字:order_poster(訂單)
	
	private String service;//刊登標題   SQL名字:service
	private String serviceDetail;//刊登內容  SQL名字:servicr_detail
	private Timestamp startTime;//開始時間  SQL名字:start_time
	private Timestamp endTime;//結束時間  SQL名字:finished_time
	private Double orderPrice;//金額  SQL名字:order_price(訂單) sverice_charge(服務)
	private String area;//服務地區-城市+區  SQL名字:area
	private String serviceImg;//服務圖片
	
	private Integer accountId;//應徵者ID 	SQL名字:applicant_account
	private String accountName;//應徵者名字  SQL名字:account_name
	private Integer applyStatus;//應徵的狀態 0:未應徵 1:已應徵 SQL名字:apply_status
	private Integer applicationResult;//應徵結果 0:未得標 1:已得標 SQL名字:application_result
	
	private Integer memberNo;//使用者編號
	private Integer reject;//拒絕狀態

}
