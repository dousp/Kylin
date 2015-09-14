package com.dou.kylin.security.shiro.common.realm;

/**
 * Created by Dou on 2015/2/10 0010.
 */

import com.dou.kylin.security.shiro.CaptchaUsernamePasswordToken;
import com.dou.kylin.security.shiro.JpaRealmRepository;
import com.dou.kylin.sys.entity.*;
import com.dou.kylin.sys.entity.Account;
import com.dou.kylin.sys.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 自定义的指定Shiro验证用户登录的类
 */
@Component
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(SystemAuthorizingRealm.class);
    private static final String ACC_STATE = "1";

    /*@Autowired
    JpaRealmRepository jpaRealmRepository;*/

    @Autowired
    private AccountService accountService;



    /**
     * 为当前登录的Subject授予角色和权限
     *  经测试:本例中该方法的调用时机为需授权资源被访问时
     *  经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     *  个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     *  比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
       //todo 角色授权
       /* String username = principals.getPrimaryPrincipal().toString();
        User user = this.jpaRealmRepository.findUserByName(username);
        if (null != user)
        {
            SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
            for (Role role : user.getRoles())
            {
                authorization.addStringPermissions(role.getPermissions());
            }
            return authorization;
        }
        return null;*/

        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userService.findRoles(username));
//        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }


    /**
     * 验证当前登录的Subject
     * 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
        //UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getPrincipal().toString();
        //User user = this.jpaRealmRepository.findUserByName(username);

        Account user = accountService.selectByUsername(username);

        if(user == null) {
            log.error("没有相关用户!");
            throw new UnknownAccountException();//没找到帐号
        }
        if(ACC_STATE.equals(user.getDisabled())) {
            throw new LockedAccountException(); //帐号锁定
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
               // user.getUsername(), //用户名
                new Principal(user, token.isMobileLogin()),
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        /**
         * todo
         * 关闭浏览器，再打开后，虽然授权缓存了，但是认证是必须的，在认证成功后，清除之前的缓存。
         */
        //clearCache(authenticationInfo.getPrincipals());

        return authenticationInfo;
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id; // 编号
        private String username; // 登录名
        private String name; // 姓名
        private boolean mobileLogin; // 是否手机登录

//		private Map<String, Object> cacheMap;

        public Principal(Account user, boolean mobileLogin) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getNickName();
            this.mobileLogin = mobileLogin;
        }

        public Long getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public boolean isMobileLogin() {
            return mobileLogin;
        }


        @Override
        public String toString() {
            return id.toString();
        }

    }
}
