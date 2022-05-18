package project;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	Pbkdf2PasswordEncoder encoder;
	
	@Autowired
	LoginSuccessHandler successHandler;
	
	/**
	 * Configures Spring Security with a whitelist of urls accessible to all while restricting all other urls to authorized users.
	 * Overwrites the default login page and successHandler with custom implementations.
	 * Utilizes default logout functionality.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/html/create-account.html", "/html/reset-password.html", "/html/forgot-password.html", // onto next line
								"/registeraccount", "/resetpasswordemail", "/resetpassword", "/img/BenderLogoPostFix.png").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/html/login.html")
				.successHandler(successHandler)
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	/**
	 * Configures Spring Security to use jdbc authentication.
	 * Connects to the preexisting database and is configured to match the schema of that database.
	 * Uses a password encoder to encode passwords.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select account_username,account_password,'true' "
					+ "from account "
					+ "where account_username = ?")
			.authoritiesByUsernameQuery("select account_username,'USER' "
					+ "from account "
					+ "where account_username = ?")
			.passwordEncoder(encoder);
	}
	
	

}
