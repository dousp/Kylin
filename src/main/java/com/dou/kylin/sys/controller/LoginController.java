package com.dou.kylin.sys.controller;

import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.common.page.demo01.Page;
import com.dou.kylin.common.web.BaseController;
import com.dou.kylin.sys.entity.Account;
import com.dou.kylin.sys.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by dou on 15/7/24.
 */
@Controller
@RequestMapping("${adminPath}")
public class LoginController extends BaseController{

    @Autowired
    private AccountService accountService;

    @RequestMapping(value ="",method = RequestMethod.GET)
    public String index(Model model,HttpServletRequest request, HttpServletResponse response){

        model.addAttribute("message", "Hello world! index");
        return ""+adminPath+"/sys/login";
    }


    @RequestMapping(value ="/login",method = RequestMethod.GET)
    public String login(Model model,Pagination pagination,Account account){

        Page<Account> page = new Page<Account>();

        List<Account> list = accountService.listPage(account,pagination);
        model.addAttribute("message", "Hello world! login");
        model.addAttribute("list", list);

        return adminPath+"/sys/login";
    }

}
