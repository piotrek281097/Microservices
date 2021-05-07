package pp.books.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import pp.users.security.jwt.AuthEntryPointJwt;
//import pp.users.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private AuthEntryPointJwt jwtAuthenticationEntryPoint;
//
//    private UserDetailsService jwtUserDetailsService;
//
//    @Autowired
//    public WebSecurityConfig(AuthEntryPointJwt jwtAuthenticationEntryPoint, UserDetailsService jwtUserDetailsService){
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtUserDetailsService = jwtUserDetailsService;
//    }
//
//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
//            throws Exception {
//        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/courier/**", "/distancesBeetwenCities/**").hasRole("MANAGER")
                .antMatchers("/books/**").hasAnyRole("USER", "ADMIN")
                .and()
//                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic().disable()
                .formLogin().disable();
    }

}
