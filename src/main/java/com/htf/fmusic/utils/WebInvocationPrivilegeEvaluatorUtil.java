package com.htf.fmusic.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Component;

/**
 * @author HTFeeds
 */
@Component
public class WebInvocationPrivilegeEvaluatorUtil {
    private static WebInvocationPrivilegeEvaluator wipe = null;

    private WebInvocationPrivilegeEvaluatorUtil() {
    }

    public static WebInvocationPrivilegeEvaluator getWebInvocationPrivilegeEvaluator() {
        return wipe;
    }

    @Autowired
    public void setWebInvocationPrivilegeEvaluator(WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator) {
        this.wipe = webInvocationPrivilegeEvaluator;
    }
}
