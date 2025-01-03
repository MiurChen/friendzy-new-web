package web.companion.pojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ComOrder {//訂單
	private Integer serviceId;//服務編號
	private Integer serviceStatus;//服務狀態
	private Integer orderId;//訂單編號
	private Integer memberId;//對方ID
	private String memberName;//對方名字
	private String memberImg;//對方頭像
	private String orderPoster;//刊登者名字
	private String orderPerson;//訂購人名字
	private Boolean posterStatus;//刊登者身分
	private String sverice;//刊登標題
	private String serviceDatil;//刊登內容
	private Timestamp startTime;//開始時間
	private Timestamp endTime;//結束時間
	private Double order_price;//金額
	private Integer orderStatus;//訂單狀態
	private String cusRateContent;//顧客評論
	private String comRateContent;//陪伴者評論
	private String cusRate;//顧客評分
	private String comRate;//陪伴者評分
	private String serviceNo;//服務地區-編號
	private String areaCity;//服務地區-城市
	private String areaDistricy;//服務地區-區
	

}
