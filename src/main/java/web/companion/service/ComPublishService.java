package web.companion.service;

import java.util.List;

import web.companion.pojo.ComArea;
import web.companion.pojo.ComPublish;
import web.companion.pojo.ComSkill;

public interface ComPublishService {
	//主頁清單 只有我以外的
	List<ComPublish> showOtherPublishAll(Integer memberNo) throws Exception;
	//查看別人刊登明細
	ComPublish showDetailPublich(Integer memberNo, Integer serviceId) throws Exception;
	//我刊登
	int addMyPublish(ComPublish publish) throws Exception;
	//取得專長
	List<ComSkill> showAllSkill() throws Exception;
	//取得地區
	List<ComArea> showAllArea() throws Exception;
	
}
