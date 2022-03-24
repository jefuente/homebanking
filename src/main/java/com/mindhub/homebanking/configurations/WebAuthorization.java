package com.mindhub.homebanking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
      http.authorizeRequests()
              .antMatchers("/web/index.html","/web/css/**","web/img/**","web/js/**").permitAll()
              .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
              .antMatchers("/**").hasAuthority("CLIENT");

*/

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/","/index.html","/web/index.html","/login.html").permitAll()
                .antMatchers("/","/index.html","/web/index.html").permitAll()
                .antMatchers("/web/css/**", "/web/img/**", "/web/js/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/clients").permitAll()
               // .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/**").hasAuthority("CLIENT")
                .anyRequest().authenticated()
                .and().formLogin().permitAll();

/*
        http.authorizeRequests()
                .antMatchers("/**").permitAll();

*/

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout");
        ///si el login se hizo con exito  se remueben la bandera que pregunta por la autenticacion
        http.formLogin().successHandler((req, res, auth) -> {
            this.clearAuthenticationAttributes(req);
        });

        http.formLogin().failureHandler((req, res, exc) -> {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        });

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        });

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}