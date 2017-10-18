package com.sanjok.springbootdataangular1crud.service.security;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    private DaoAuthenticationProvider daoAuthenticationProvider;


    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(DaoAuthenticationProvider authenticationProvider) {
        this.daoAuthenticationProvider = authenticationProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder(final StrongPasswordEncryptor passwordEncryptor) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        passwordEncoder.setPasswordEncryptor(passwordEncryptor);
        return passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(final UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider);
        super.configure(authenticationManagerBuilder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/user/all", "/login", "/index.html", "/dashBoard.html").permitAll()
                // .antMatchers("/admin/**").hasAnyRole("ADMIN")
                //  .antMatchers("/user/**").hasAuthority("ROLE_ADMIN")
                // .antMatchers("/user/add").hasAnyRole("ROLE_ADMIN")


                .anyRequest().authenticated()
                .antMatchers("/resources/**").hasAnyAuthority().anyRequest().permitAll()
                .and()
                .formLogin().loginProcessingUrl("/api/login")
                .and().logout().logoutUrl("/api/logout").logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
                .and().authenticationProvider(daoAuthenticationProvider)
                .logout().permitAll();
        http.headers().frameOptions().disable();
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        http.formLogin().successHandler(restAuthenticationSuccessHandler).failureHandler(restAuthenticationFailureHandler);


        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        //http.csrf().disable();

    }

}
