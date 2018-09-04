package controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import po.ActiveUser;

@Controller
public class FirstAction {

    //系统首页
    @RequestMapping("/first")
    public String first(Model model) {

        //从shiro的session中获取subject
        Subject subject = SecurityUtils.getSubject();

        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();

        model.addAttribute("activeUser", activeUser);

        return "/first";
    }

    //欢迎页面
    @RequestMapping("/welcome")
    public String welcome(Model model) {
        return "/welcome";
    }
}
