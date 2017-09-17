package controller;

import entity.Shop;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.Shop_ServiceImpl;
import utils.ListAndSearchInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("Shop")
public class Shop_Controller extends Basic_Controller<Shop> {
	@Resource(name = "Shop_ServiceImpl")
    Shop_ServiceImpl service;






}
