/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.dou.kylin.security.shiro;


import com.dou.kylin.common.utils.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 表单验证（包含验证码）过滤类
 * @author ThinkGem
 * @version 2014-5-19
 */
@Service("captchaFormAuthenticationFilter")
public class CaptchaFormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;

    @Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password==null){password = "";}
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest) request);
		String captcha = getCaptcha(request);
		boolean mobile = isMobileLogin(request);
		return new CaptchaUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
	}

    /**
     *  验证码校验
     */
    protected void doCaptchaValidate(HttpServletRequest request,CaptchaUsernamePasswordToken token) {
        //session中的图形码字符串
        String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //比对
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }
    /**
     * 登录验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = createToken(request, response);
        String username = token.getUsername();
        try {
            /*图形验证码验证*/
           // doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);//正常验证
            LOG.info(token.getUsername()+"登录成功");
            return onLoginSuccess(token, subject, request, response);

        }catch(IncorrectCaptchaException e){
            request.setAttribute("message_login", "验证码错误");
            LOG.info(username+"验证码错误--"+e);
            return onLoginFailure(token, e, request, response);

        }catch(UnknownAccountException e){
            request.setAttribute("message_login", "未知账户");
            LOG.info(username+"未知账户--"+e);
            return onLoginFailure(token, e, request, response);

        }catch(IncorrectCredentialsException e){
            request.setAttribute("message_login", "密码不正确");
            LOG.info(username+"密码不正确--"+e);
            return onLoginFailure(token, e, request, response);

        }catch(LockedAccountException e){
            request.setAttribute("message_login", "账户已锁定");
            LOG.info(username+"账户已锁定--"+e);
            return onLoginFailure(token, e, request, response);

        }catch(ExcessiveAttemptsException e){
            request.setAttribute("message_login", "用户名或密码错误次数过多");
            LOG.info(username+"用户名或密码错误次数过多--"+e);
            return onLoginFailure(token, e, request, response);

        }catch(AuthenticationException e){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            request.setAttribute("message_login", "用户名或密码不正确");
            LOG.info(username+"登录失败--"+e);
            return onLoginFailure(token, e, request, response);
        }

    }


 /*   @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        //issueSuccessRedirect(request, response);
        //we handled the success redirect directly, prevent the chain from continuing:
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))
                || request.getParameter("ajax") == null) {// 不是ajax请求
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + this.getSuccessUrl());

        } else {
            httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
        }
        return false;
    }*/


    public String getCaptchaParam() {
		return captchaParam;
	}

    protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}
	
	protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, getMobileLoginParam());
    }

    public void setMobileLoginParam(String mobileLoginParam) {

        this.mobileLoginParam = mobileLoginParam;
    }

	public String getMessageParam() {
		return messageParam;
	}

    public void setMessageParam(String messageParam) {
        this.messageParam = messageParam;
    }

    @Override
    public String getSuccessUrl() {
        return super.getSuccessUrl();
    }

}