package controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.CustomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

//    //用户提交登录方法
//    @RequestMapping("/login")
//    public String login(HttpSession session, String usercode, String password) {
//        //调用service校验用户账号的正确性
//        //与shiro结合
//
//
//        //如果用户身份校验通过，将身份信息写到session中
//        session.setAttribute("usercode", usercode);
//
//        //重定向到商品查询页面
//        return "redirect:/items/queryItems.action";
//    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws Exception {
        //如果登录失败从request中获取认证信息，shiroLoginFailure就是shiro异常类的限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else if ("randomCodeError".equals(exceptionClassName)) {
                throw new CustomException("验证码错误");
            } else {
                throw new Exception();
            }
        }

        return "login";
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
