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

    public void selectByAll(int[] trem, int[] compare, String[] text, int[] join, ModelMap m) {
		SeachInfo sea = new SeachInfo();
		StringBuilder sb = new StringBuilder("where ");
		sea.setTable("User");
		for (int i = 0; i < trem.length; i++) {
			sea.setCol(StatusUtils.UserSelectMap.get(trem[i]));
			sea.setMath(StatusUtils.compareMap.get(compare[i]));
			sea.setValue(text[i]);
			sb.append(sea.getNoWhere());
			if (join[i] == 0) {
				sb.append(" and ");
			} else {
				sb.append(" or  ");
			}
		}
		String where = sb.toString().substring(0, sb.length() - 4);
		sea.setWhere(where);
		sea.setPageno(1);
		sea.setRowcount(mapper.getSize(sea));
		List<User> list = mapper.getAll(sea);
		m.put("where",where);
		m.put("list", list);
		m.put("info", sea);
    }
}
