import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AuthorizationTest {

    //角色授权测试和资源授权测试
    @Test
    public void testAuthorization() {

        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:resources/shiro/shiro-permission.ini");

        SecurityManager securityManager = securityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        System.out.println("认证状态:" + subject.isAuthenticated());

        //认证通过之后才能授权
        //第一种授权方式是基于角色的授权，hasRole传入角色的标识
        boolean ishasRole = subject.hasRole("role1");
        System.out.println("单个角色判断" + ishasRole);

        //hasRoles是否拥有多个角色
        boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("多个角色判断" + hasAllRoles);

        //使用check方法进行授权，如果授权不通过会抛异常
        try {
            subject.checkRole("role3");
        } catch (UnauthorizedException e) {
            System.out.println("没有该角色权限");
        }


        //第二种授权方式是基于资源的授权，isPermitted传入权限标识符
        boolean permitted = subject.isPermitted("user:create");//该用户是否拥有对user资源进行创建create的权限
        System.out.println("单个权限判断" + permitted);

        //多个权限判断
        boolean permittedAll = subject.isPermittedAll("user:create:1", "user:update");
        System.out.println("多个权限判断" + permittedAll);
    }

    @Test
    public void testAuthorizationCustomRealm() {
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:resources/shiro/shiro-realm.ini");

        SecurityManager securityManager = securityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        System.out.println("认证状态:" + subject.isAuthenticated());

        //基于资源的授权,调用isPermitted方法会调用CustomRealm从数据库查询正确的权限数据
        // isPermitted传入权限标识符，判断user:create:1是否在CustomRealm查询到的权限数据之内
        boolean permitted = subject.isPermitted("user:create:1");
        System.out.println("单个权限判断" + permitted);

        //多个权限判断
        boolean permittedAll = subject.isPermittedAll("user:create:1", "user:create");
        System.out.println("多个权限判断" + permittedAll);

    }
}
