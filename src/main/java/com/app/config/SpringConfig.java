package com.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //mandatory cls level anno tation to tell SCfollowing cls is equivalent
//to bean-config.xml going to contain bean config: @Bean

@EnableWebSecurity //to enable web security support
@EnableGlobalMethodSecurity(prePostEnabled = true) //to enable method level authorization rules
public class SpringConfig {
	//dep passwordEncoder
	@Autowired
	private PasswordEncoder enc;
	
	//configure a bean for authentication: in mean auth,where user details are not persistent: since going 
	//tp be stored in memory
//	@Bean
//	public InMemoryUserDetailsManager myAuthenticate() {
//		//this mwthod should return InMemoryUserDetailsManager instance loaded with :
//		//InMemoryUserDetailsManager(UserDetails ...details)
//		//UserDetails i/f <---User class (String nm,String pswd,Collection<GrantedAuthority> coll)
//		User admin = new User("sarah", enc.encode("1234"), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
//		User cust1 = new User("Mohsin", enc.encode("12345"), List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
//		User cust2 = new User("sabad", enc.encode("123456"), List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
//		
//		return new InMemoryUserDetailsManager(admin,cust1,cust2);
//	} //for DB provided thats why commented
	
	//configure another bean for authorization
	@Bean
	public SecurityFilterChain myAuthprization(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests() //telling Spring security to authorize all request
		.antMatchers("/products/view").permitAll() //no authentication or authorization required
		.antMatchers("/products/purchase").hasRole("CUSTOMER")//only customer can purchase
		.antMatchers("/products/add").hasRole("ADMIN") //only admin add product
		.anyRequest() //any other remaining end points
		.authenticated() //must be: authenticated
		.and() //bridge
		.httpBasic(); //support Basic authentication
		return http.build();
	}
}
