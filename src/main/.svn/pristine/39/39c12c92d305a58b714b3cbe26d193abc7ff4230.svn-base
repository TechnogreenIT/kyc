package com.tes.config;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import com.tes.handler.CustomAuthenticationFailureHandler;
import com.tes.handler.UserAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private UserAuthenticationSuccessHandler successHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Bean
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/api/auth/**", "/resources/**", "/rest/**", "/login", "/ajax-tanent", "/ajax-tanent1")
				.permitAll()
				/* .antMatchers("/api/auth/signin").hasAnyRole("ADMIN","USER","ENVROFFICER","THIRDPARTY") */
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/superadmin/**").access("hasRole('SUPERADMIN')")
				.antMatchers("/env/**").access("hasRole('ENVROFFICER')")
				.antMatchers("/thirdParty/**").access("hasRole('THIRDPARTY')")
				.antMatchers("/management/**").access("hasRole('MANAGEMENT')")
				.anyRequest().authenticated().and().formLogin().successHandler(successHandler)
				.loginPage("/login")
				.loginPage("/login").failureHandler(customAuthenticationFailureHandler)
				.permitAll()
				.and().logout()
				.and().exceptionHandling().accessDeniedPage("/Access_Denied");
		http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resources/**", "/", "/category", "/newAssets/**");
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").setCacheControl(CacheControl.maxAge(172800, TimeUnit.SECONDS));
	}

}
