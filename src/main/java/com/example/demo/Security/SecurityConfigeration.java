package com.example.demo.Security;


import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigeration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SSUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                .antMatchers("/list","/","/login","/h2-console/**",
                       "/static/css/hcss.css","/css**","/js**","/static/css/tcss.css",
                        "/register","/home","/login","/css/hcss.css","/css/tcss.css" ,"/../static/css/sercss.css","/static/css/sercss.css"
                ,"/css/sercss.css","/../static/css/sercss.css","/searchitem","/css/regcss.css",
                        "/static/css/regcss.css","/static/images/**","/templates/images/**","/images/**",
                        "/petList","/clothList","/othersList","/../static/css/regcss.css","/css/regcss.css").permitAll()
                .antMatchers("/itemform","/listmy").access("hasAnyAuthority('USER', 'ADMIN')")
                .antMatchers("/update/item/{id}","/postforregistereduser").access("hasAuthority('ADMIN') ")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/listmy")
                .loginProcessingUrl("/loginprocess")
                .permitAll()

                .and()
                .logout().logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home").permitAll()
//                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .httpBasic();
        http
                .csrf()
                .disable();
        http
                .headers().frameOptions().disable();



    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception{
//        auth.inMemoryAuthentication()
//              .withUser("app").password("pwd").authorities("APPLICANT")
//                .and()
//                .withUser("emp").password("pwd").authorities("EMPLOYER")
//                .and()
//                .withUser("rec").password("pwd").authorities("RECRUITER");



        auth
                .userDetailsService(userDetailsServiceBean());
    }

}
