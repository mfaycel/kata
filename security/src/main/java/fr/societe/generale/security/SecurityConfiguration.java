//package fr.societe.generale.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
///**
// * 
// * @author mbarki 2 févr. 2018 10:20:19
// *
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	private static String REALM="TEST_FMBARKI";
//	
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("fmbarki").password("fmbarki").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("test").password("test").roles("USER");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
// 
//	  http.csrf().disable()
//	  	.authorizeRequests()
//	  	.antMatchers("/user/**").hasRole("ADMIN")
//		.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
// 	}
//	
//	@Bean
//	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
//		return new CustomBasicAuthenticationEntryPoint();
//	}
//	
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
