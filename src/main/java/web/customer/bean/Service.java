package web.customer.bean;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Service {
	private Integer service_id;
	private Integer service_poster;
	private  String sverice;
	private String servicr_detail;
	private String service_pic;
	private Timestamp start_time;
	private Timestamp finished_time;
	private Double sverice_charge;
	private Integer service_status;
	private String service_location;
}
