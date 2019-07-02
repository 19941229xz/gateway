package com.example.gateway.common;

import com.example.gateway.dao.UserDao;
import com.example.gateway.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserDao userDao;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JwtUtil.getUsername(principalCollection.toString());
        User user = userDao.getUserByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo =
                new SimpleAuthorizationInfo();

//        Set<String> roleSet= new HashSet<String>();
//        roleSet.add(user.getRoleId());
//        simpleAuthorizationInfo.setStringPermissions(roleSet);
        simpleAuthorizationInfo.addRole(user.getRoleId());
        return simpleAuthorizationInfo;


    }


    // 用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();

        String username = JwtUtil.getUsername(token);

        if (username == null) {
            throw new AuthenticationException("token 无效");
        }

        User user = userDao.getUserByUsername(username);

        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }

        if (!JwtUtil.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("用户名和密码错误");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
