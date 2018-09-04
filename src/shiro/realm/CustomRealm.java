package shiro.realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import po.ActiveUser;
import po.SysPermission;
import service.SysService;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SysService sysService;

    @Override
    public void setName(String name) {
        //这是Realm的名称
        super.setName("customRealm");
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真是身份类型(在上边的goGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo)
        String usercode = (String) principalCollection.getPrimaryPrincipal();

        //根据身份信息获取权限信息
        //模拟从数据库中获取到的动态权限数据
        List<String> permissions = new ArrayList<>();
        permissions.add("user:create"); //模拟user的创建权限
        permissions.add("items:add"); //模拟商品的添加权限

        //查询权限数据，返回授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //simpleAuthorizationInfo
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //token是用户输入的
        //第一步，从token中获取身份信息
        String usercode = (String) authenticationToken.getPrincipal();

        //第二步：根据用户输入的usercode从数据库中查询呢

        //模拟从数据库中查找到的密码
        String password = "111111";
//activeUser就是用户的身份信息
        ActiveUser activeUser=new ActiveUser();
        activeUser.setUserid("zhangsan");
        activeUser.setUsercode("zhangsan");
        activeUser.setUsername("张三");

        //根据用户id取出菜单
        //通过service取出菜单
        List<SysPermission> menus=null;
        try {
            menus=sysService.findMenuListByUserId("zhangsan");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //将用户菜单设置到activeUser
        activeUser.setMenus(menus);


        //如果查不到返回null，

        //如果查询到，返回认证信息AuthenticationInfo

        ///将activeUser设置到simpleAuthenticationInfo
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(usercode, password, this.getName());

        return authenticationInfo;
    }

}
