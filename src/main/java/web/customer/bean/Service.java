package web.customer.bean;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Service {
	private Integer service_id;
	private Integer service_poster;
	private String service;
	private String service_detail;
	private String service_pic;
	private Long start_time;
	private Long finished_time;
	private Double service_charge;
	private Integer service_status;
	private String service_location;
	private Integer poster_status;
	private String member_name;
}
