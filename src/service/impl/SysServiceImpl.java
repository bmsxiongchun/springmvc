package service.impl;

import mappers.SysPermissionMapper;
import mappers.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import po.ActiveUser;
import po.SysPermission;
import po.SysUser;
import po.SysUserExample;
import service.SysService;
import utils.CustomException;
import utils.MD5;

import java.util.List;

public class SysServiceImpl implements SysService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public ActiveUser authenticat(String userCode, String password) throws Exception {
        /*
            认证过程：
            根据用户身份账号查询数据库，如果查询不到用户不存在
            对输入的密码和数据库密码进行比对，如果一致，认证通过
         */

        SysUser sysUser = this.findUserByUserCode(userCode);

        if (sysUser == null) {
            throw new CustomException("用户不存在");
        }

        String password_db = sysUser.getPassword();

        String password_input_md5 = new MD5().getMD5ofStr(password);
        if (!password_input_md5.equalsIgnoreCase(password_db)) {
            throw new CustomException("用户名/密码错误");
        }

        String id = sysUser.getId();

        List<SysPermission> menus = this.findMenuListByUserId(id);

        List<SysPermission> permissions = this.findPermissionListByUserId(id);

        ActiveUser user = new ActiveUser();
        user.setMenus(menus);
        user.setPermissions(permissions);
        user.setUsercode(userCode);
        user.setUsername(sysUser.getUsername());
        return user;
    }

    @Override
    public SysUser findUserByUserCode(String userCode) throws Exception {
        SysUserExample sysUserExample = new SysUserExample();

        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andUsercodeEqualTo(userCode);

        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);

        if (sysUserList!=null&&sysUserList.size()==1) {
            return sysUserList.get(0);
        }

        return null;
    }

    @Override
    public List<SysPermission> findMenuListByUserId(String userId) throws Exception {
        return null;
    }

    @Override
    public List<SysPermission> findPermissionListByUserId(String userId) throws Exception {
        return null;
    }
}
