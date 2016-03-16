package com.htf.fmusic.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.htf.fmusic.common.AjaxAuthenticationFailureHandler;
import com.htf.fmusic.common.AjaxAuthenticationSuccessHandler;
import com.htf.fmusic.common.CustomLogoutSuccessHandler;

import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * @author HTFeeds
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	AjaxAuthenticationSuccessHandler ajaxLoginSuccessHandler;
	
	@Autowired
    AjaxAuthenticationFailureHandler ajaxLoginFailureHandler;
	
	@Autowired
	CustomLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        //.antMatchers("/login").access("isAnonymous()")
        .antMatchers("/admin**").access("hasRole('ADMIN')")
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .and().formLogin().loginPage("/login").successHandler(ajaxLoginSuccessHandler).failureHandler(ajaxLoginFailureHandler).permitAll()
        .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(864000)
        .and().logout().deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler)
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/access_denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Spring Security ignores request to static resources such as CSS or JS files.
        web
            .ignoring()
            .antMatchers("/static/**");
            //.antMatchers(HttpMethod.POST, "/rest/**")
            //.antMatchers(HttpMethod.POST, "/admin/**")
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}
}
