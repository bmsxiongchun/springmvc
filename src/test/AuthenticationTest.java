
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthenticationTest {

    //用户的登录和退出
    @Test
    public void testLoginAndLogout() {

        //创建securityManager工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:resources/shiro/shiro.ini");

        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();

        //将securityManager设置到当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        //从SecurotyUtils中获取一个subject
        Subject subject = SecurityUtils.getSubject();

        //在提交认证之前准备token
        //模拟用书输入的用户名和密码，将来是由用书从login页面传过来的
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");

        //提交认证
        try {
            subject.login(token);
        } catch (Exception e) {
            //认证失败
            e.printStackTrace();
        }

        boolean authenticated = subject.isAuthenticated();

        System.out.println("是否认证通过" + authenticated);

        //退出操作
        subject.logout();

    }
}
