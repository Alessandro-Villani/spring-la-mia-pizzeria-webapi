package org.java.pizzeria.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {
	
	@Bean
	PasswordEncoder passwordEncoder() {
	
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		return 
			http.authorizeHttpRequests(a -> a
					.requestMatchers("/pizzas/{id}/special-offer/**").hasAuthority("ADMIN")
					.requestMatchers("/pizzas/create").hasAuthority("ADMIN")
					.requestMatchers("/pizzas/edit/**").hasAuthority("ADMIN")
			        .requestMatchers("/pizzas/**").hasAnyAuthority("USER", "ADMIN")
			        .requestMatchers("/ingredients/**").hasAuthority("ADMIN")
			        .requestMatchers("/**").permitAll()
			).formLogin(f -> f.defaultSuccessUrl("/pizzas").permitAll()
			).logout(l -> l.logoutSuccessUrl("/pizzas")
			).build();
	}
}
