package controller;

import entity.Checkdetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import serviceimpl.Checkdetail_ServiceImpl;

import javax.annotation.Resource;

@Controller
@RequestMapping("Checkdetail")
public class Checkdetail_Controller extends Basic_Controller<Checkdetail> {
    @Resource(name = "Checkdetail_ServiceImpl")
    Checkdetail_ServiceImpl service;



}
