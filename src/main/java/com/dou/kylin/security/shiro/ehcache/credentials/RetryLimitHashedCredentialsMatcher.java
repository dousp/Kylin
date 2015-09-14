package com.dou.kylin.security.shiro.ehcache.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

//  private static String CACHE_CONF ="ehcache.configFile";
 // private static String PASS_WORD_CACHE_NAME ="pwCache.name";
    private static String DEFAULT_PW_CACHE = "passwordRetryCache";
 // private static PropertiesLoader propertiesLoader = new PropertiesLoader("kylin.properties");

    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache(DEFAULT_PW_CACHE);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry count + 1
        Element element = passwordRetryCache.get(username);
        if(element == null) {
            element = new Element(username , new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }

 }
