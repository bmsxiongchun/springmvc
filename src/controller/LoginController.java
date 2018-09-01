package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //用户提交登录方法
    @RequestMapping("/login")
    public String login(HttpSession session, String usercode, String password) {
        //调用service校验用户账号的正确性
        //与shiro结合

        //如果用户身份校验通过，将身份信息写到session中
        session.setAttribute("usercode", usercode);

        //重定向到商品查询页面
        return "redirect:/items/queryItems.action";
    }

    //用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        //session失效
        session.invalidate();

        //重定向到商品查询页面
        return "redirect:/items/queryItems.action";
    }
}
