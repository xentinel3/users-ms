package mx.com.think.usersms.security;

import mx.com.think.usersms.service.UsersService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

   final
   UsersService usersService;
   final
   BCryptPasswordEncoder bCryptPasswordEncoder;
    final
    Environment environment;

   public WebSecurity(UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment environment) {
      this.usersService = usersService;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      this.environment = environment;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception{
      http.csrf().disable();
      http.authorizeHttpRequests().antMatchers("/users/status/check").permitAll()
              .and().addFilter(getAuthenticationFilter());
      http.headers().frameOptions().disable();
   }

   private AuthenticationFilter getAuthenticationFilter() throws Exception{
      AuthenticationFilter authenticationFilter =  new AuthenticationFilter(usersService, environment, authenticationManager());
      authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
      return authenticationFilter;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
   }
}
