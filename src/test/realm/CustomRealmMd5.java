package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealmMd5 extends AuthorizingRealm {

    @Override
    public void setName(String name) {
        super.setName("CustomRealmMd5");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //token是用户输入的
        //第一步，从token中获取身份信息
        String usercode = (String) authenticationToken.getPrincipal();

        //第二步：根据用户输入的usercode从数据库中查询呢

        //模拟从数据库中查找到的密码
//        String password = "111111";

        //如果查不到返回null
        //如果查询到，返回认证信息AuthenticationInfo
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(usercode, password, this.getName());

        //模拟用户从数据库查询到的密码散列值
        String password = "f3694f162729b7d0254c6e40260bf15c";
        //从数据库查询salt
        String salt = "qwerty";
        // 上边散列值和salt对应的密码为111111

        //如果查询到返回信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(usercode, password, ByteSource.Util.bytes(salt), this.getName());
        return authenticationInfo;
    }
}
