package com.live.cconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.belief.module.services.UserService;

/**
 * 该类从被扫描的包中移出来，也没有直接指定加入WebAppInitializer的RootConfigClasses，需要查找该类被什么类扫描注入
 * 
 * @author YuDongwei
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final int validSeconds = 3600;
	private static final String rememberKey = "";
	@Autowired
	private UserService userService;
	// @Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	// @Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	// @Bean
	public RememberMeServices rememberMeServices() {
		PersistentTokenBasedRememberMeServices svc = new PersistentTokenBasedRememberMeServices(rememberKey, userService, persistentTokenRepository());
		svc.setAlwaysRemember(true);
		svc.setTokenValiditySeconds(validSeconds);
		svc.setCookieName("authToken");
		return svc;
	}
}
