package web.companion.service;

import java.util.List;

import web.companion.pojo.ComApplicant;
import web.companion.pojo.ComOrder;

public interface ComApplicantService{
	
	List<ComApplicant> showAllApplocant(Integer memberNo)throws Exception;
	
	ComApplicant showApplocantById(Integer meberNo , Integer account , Integer serviceId)throws Exception;
	
}
