//package epam.com.springBoot.api.config;
//
//import epam.com.springBoot.service.impl.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder builder, @Autowired CustomUserDetailsService service) throws Exception {
//        builder.userDetailsService(service).passwordEncoder(passwordEncoder());
//    }

//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests().antMatchers("/api/v1/admin/*").hasAuthority(Role.ADMIN.name())
//                .antMatchers("/api/v1/users/*", "/api/v1/activities/*").hasAuthority(Role.CLIENT.name());
//    }
    //anonymous for login registration page logout
//}
