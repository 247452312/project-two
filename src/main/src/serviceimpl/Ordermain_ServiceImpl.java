package serviceimpl;


import entity.Ordermain;
import mapper.Ordermain_Mapper;
import org.springframework.stereotype.Service;
import service.Ordermain_Service;

import javax.annotation.Resource;

@Service("Ordermain_ServiceImpl")
public class Ordermain_ServiceImpl extends Basic_ServiceImpl<Ordermain> implements Ordermain_Service {
	@Resource(name="Ordermain_Mapper")
	Ordermain_Mapper mapper;

}
