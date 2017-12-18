package bankaccount.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import bankaccount.model.security.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() 
				.withUser("Silva").password("1234").roles("USER") 
				.and() 
				.withUser("banka").password("banka").roles("ADMIN");
			
	}
	
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
        	.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/clientcreate").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    
/*
	@Autowired
	void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //
				.withUser("Silva").password("1234").authorities("ROLE_USER") //
				.and() //
				.withUser("banka").password("banka").authorities("ROLE_ADMIN");
	}
    
    
    
    @Bean
    public UserDetailsService userDetailsService(){
    	
    	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    	manager.createUser(User.withUsername("banka").password("banka").roles("ADMIN").build());
    	manager.createUser(User.withUsername("Silva").password("1234").roles("ADMIN").build());
    	
    	return manager;
    	
    }*/
}


