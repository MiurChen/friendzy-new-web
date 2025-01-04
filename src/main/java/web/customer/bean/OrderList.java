package web.customer.bean;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderList {
	private String member_name;
	private Integer order_id;
	private Integer service_idno;
	private Integer order_person;
	private Integer customer_rating;
	private String  customer_rate_content;
	private Integer companion_rate;
	private String  companion_rate_content;
	private Double  order_price;
	private Integer order_status;
	private Integer order_poster;
	private String order_title;
	
}
