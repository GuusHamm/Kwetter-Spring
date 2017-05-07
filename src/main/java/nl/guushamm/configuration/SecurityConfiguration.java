package nl.guushamm.configuration;

import nl.guushamm.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Created by guushamm on 13-3-17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private SpringDataJpaUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(Account.passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilterBefore(new CorsFilter(corsConfigurationSource()), ChannelProcessingFilter.class)
				.authorizeRequests()
					.antMatchers("built/**", "/main.css","/websocket/**").permitAll()
					// .antMatchers("built/**", "/main.css","info**","/info**").permitAll()
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.defaultSuccessUrl("/thymeleaf/", true)
					.permitAll()
					.and()
					.httpBasic()
					.and()
				.csrf().disable()
				.logout()
					.logoutSuccessUrl("/");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
