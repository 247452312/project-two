package controller;

import entity.User;
import entity.Vip;
import entity.Viptype;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Viptype_ServiceImpl;
import utils.JsonData1;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("Viptype")
public class Viptype_Controller extends Basic_Controller<Viptype> {
	@Resource(name = "Viptype_ServiceImpl")
    Viptype_ServiceImpl service;



}
