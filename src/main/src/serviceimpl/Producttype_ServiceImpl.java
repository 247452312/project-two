package serviceimpl;


import entity.Producttype;
import mapper.Producttype_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.Producttype_Service;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("Producttype_ServiceImpl")
public class Producttype_ServiceImpl extends Basic_ServiceImpl<Producttype> implements Producttype_Service{
	@Resource(name="Producttype_Mapper")
    Producttype_Mapper mapper;

}
