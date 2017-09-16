package serviceimpl;


import entity.Viptype;
import mapper.Viptype_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Viptype_Service;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("Viptype_ServiceImpl")
public class Viptype_ServiceImpl extends Basic_ServiceImpl<Viptype> implements Viptype_Service{
	@Resource(name="Viptype_Mapper")
	Viptype_Mapper mapper;


}
