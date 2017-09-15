package controller;

import entity.Producttype;
import entity.Viptype;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import serviceimpl.Producttype_ServiceImpl;
import utils.SeachInfo;
import utils.StatusUtils;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("Producttype")
public class Producttype_Controller extends Basic_Controller<Producttype> {
	@Resource(name = "Producttype_ServiceImpl")
	Producttype_ServiceImpl service;




}
