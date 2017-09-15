package controller;

import entity.Orderdetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import serviceimpl.Orderdetail_ServiceImpl;

import javax.annotation.Resource;

@Controller
@RequestMapping("Orderdetail")
public class Orderdetail_Controller extends Basic_Controller<Orderdetail> {
	@Resource(name = "Orderdetail_ServiceImpl")
    Orderdetail_ServiceImpl service;

}
