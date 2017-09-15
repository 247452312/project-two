package serviceimpl;


import entity.Orderdetail;
import mapper.Orderdetail_Mapper;
import org.springframework.stereotype.Service;
import service.Orderdetail_Service;

import javax.annotation.Resource;

@Service("Orderdetail_ServiceImpl")
public class Orderdetail_ServiceImpl extends Basic_ServiceImpl<Orderdetail> implements Orderdetail_Service{
	@Resource(name="Orderdetail_Mapper")
	Orderdetail_Mapper mapper;

}
