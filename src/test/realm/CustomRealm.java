package realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {

    @Override
    public void setName(String name) {
        //这是Realm的名称
        super.setName("customRealm");
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //token是用户输入的
        //第一步，从token中获取身份信息
        String usercode = (String) authenticationToken.getPrincipal();

        //第二步：根据用户输入的usercode从数据库中查询呢

        //模拟从数据库中查找到的密码
        String password = "111111";

        //如果查不到返回null
        //如果查询到，返回认证信息AuthenticationInfo
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(usercode, password, this.getName());

        return authenticationInfo;
    }



}
