package com.yanziting.config.shiro;

import com.yanziting.model.DTO.UserDTO;
import com.yanziting.service.UserService;
import com.yanziting.utils.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String usernameOrEmail = (String)authenticationToken.getPrincipal();
        String pwd = new String((char[]) authenticationToken.getCredentials());

        //从数据库中取用户
        UserDTO userDTO = userService.getUserByUsernameOrEmailAndPwd(usernameOrEmail,pwd);
        if (null == userDTO){
            throw new UnknownAccountException("账号或密码错误");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(usernameOrEmail,pwd,getName());
        //将用户信息放到session中
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO,userDTO);
        return authenticationInfo;
    }
}
