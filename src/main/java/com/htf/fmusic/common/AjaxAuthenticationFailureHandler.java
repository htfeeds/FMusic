package com.htf.fmusic.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author HTFeeds
 */
@Component
public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxAuthenticationFailureHandler.class);

    public AjaxAuthenticationFailureHandler() {
        LOGGER.info("Inside constructor of AjaxAuthenticationFailureHandler.");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        if (RequestUtil.isAjaxRequest(request)) {
            RequestUtil.sendJsonResponse(response, "success", "false");
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }

}
