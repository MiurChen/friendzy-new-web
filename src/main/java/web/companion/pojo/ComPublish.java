package web.companion.pojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ComPublish {
	private Integer memberNo;//使用者編號
	
	private Integer orderId;//訂單編號  SQL名字:order_id
	private Integer serviceId;//服務編號	SQL名字:service_id
	private Integer orderStatus;//訂單狀態  SQL名字:order_status
	private Integer serviceStatus;//服務狀態 SQL名字:service_status
	
	private Integer theirId;//對方ID  SQL名字:theirId
	private String theirName;//對方名字  SQL名字:theirName
	private String theirImg;//對方頭像  SQL名字:theirImg
	private String posterName;//刊登者名字  SQL名字:poster_name
	private Integer poster;//刊登者Id  SQL名字:poster(訂單)
	private Integer posterStatus;//刊登者身分 SQL名字:poster_status
	
	private String service;//刊登標題   SQL名字:service
	private String serviceDetail;//刊登內容  SQL名字:servicr_detail
	private Timestamp startTime;//開始時間  SQL名字:start_time
	private Timestamp endTime;//結束時間  SQL名字:finished_time
	private Double charge;//金額  SQL名字:order_price(訂單) sverice_charge(服務)
	private String area;//服務地區-城市+區  SQL名字:area
	private String serviceImg;//服務圖片 SQL名字：service_img

	private Integer accountId;//應徵者ID 	SQL名字:applicant_account
}
