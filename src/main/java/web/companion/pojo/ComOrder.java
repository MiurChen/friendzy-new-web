package web.companion.pojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ComOrder {//訂單
	private Integer serviceId;//服務編號  SQL名字:service_id
	private Integer serviceStatus;//服務狀態  SQL名字:service_status
	private Integer orderId;//訂單編號  SQL名字:order_id
	private String theirName;//對方名字  SQL名字:theirName
	private Integer theirId;//對方ID  SQL名字:theirId
	private String theirImg;//對方頭像  SQL名字:theirImg
	private String orderPosterName;//刊登者名字  SQL名字:poster_name
	private Integer orderPoster;//刊登者Id  SQL名字:order_poster(訂單)
	private String orderPersonName;//訂購人名字  SQL名字:person_name
	private Integer orderPerson;//訂購人Id  SQL名字:order_person
	private Boolean posterStatus;//刊登者身分   SQL名字:poster_status
	private String service;//刊登標題   SQL名字:service
	private String serviceDatil;//刊登內容  SQL名字:servicr_detail
	private Timestamp startTime;//開始時間  SQL名字:start_time
	private Timestamp endTime;//結束時間  SQL名字:finished_time
	private Double order_price;//金額  SQL名字:order_price
	private Integer orderStatus;//訂單狀態  SQL名字:order_status
	private String cusRateContent;//顧客評論  SQL名字:customer_rate_content
	private String comRateContent;//陪伴者評論  SQL名字:companion_rate_content
	private String cusRate;//顧客評分  SQL名字:customer_rate
	private String comRate;//陪伴者評分  SQL名字:companion_rate
	private String serviceNo;//服務地區-編號  SQL名字:area_no
	private String areaCity;//服務地區-城市  SQL名字:area_city
	private String areaDistricy;//服務地區-區  SQL名字:area_district
}
