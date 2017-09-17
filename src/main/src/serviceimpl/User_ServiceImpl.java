package serviceimpl;


import entity.User;
import mapper.User_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import service.User_Service;
import utils.Info;
import utils.MD5Util;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("User_ServiceImpl")
public class User_ServiceImpl extends Basic_ServiceImpl<User> implements User_Service{
	@Resource(name="User_Mapper")
	User_Mapper mapper;

	@Override
	public void insert(User user) {
		user.setCreatedate(Info.getNow());
		user.setPass(MD5Util.getMD5("123"));
		super.insert(user);
	}

	@Override
	public void update(User user) {
		User u = mapper.getById(user.getId());
		user.setPass(u.getPass());
		user.setCreatedate(u.getCreatedate());
		super.update(user);
	}


	@Override
	public User login(String user, String pass) {

		return mapper.login(user,MD5Util.getMD5(pass));
	}
}
