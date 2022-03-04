package pl.witoldbrzezinski.transportmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.authorizeRequests()
				.mvcMatchers(HttpMethod.GET, "/").permitAll()	
				 .anyRequest().authenticated()
				 .and()
				 .formLogin()
			     .loginPage("/login").permitAll()
//			     .loginProcessingUrl("/login")
			     .defaultSuccessUrl("/", true)
//			     .failureUrl("/login.html?error=true")
			     .and()
				 .httpBasic();
	}

	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	      .withUser("user")
	      .password("{noop}user")
	      .roles("USER")
	    .and()
	      .withUser("admin")
	      .password("{noop}admin")
	      .roles("ADMIN");
	  }
	
	
}


