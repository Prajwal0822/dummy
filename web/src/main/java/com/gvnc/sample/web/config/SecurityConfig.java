package com.gvnc.sample.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.gvnc.sample.web.identity.*;
import org.springframework.core.annotation.Order;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Filters will not get executed for the resources

        web.ignoring().antMatchers("/assets/**", "/", "/h2-console/**"
                , "/*.html", "/**/*.html" ,"/**/*.css","/**/*.js","/**/*.png","/**/*.jpg", "/**/*.gif",
                "/**/*.svg", "/**/*.ico", "/**/*.ttf","/**/*.woff","/**/*.otf");
    }

    //If Security is not working check application.properties if it is set to ignore
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .exceptionHandling().and()
        .anonymous().and()
        // Disable Cross site references
        .csrf().disable()
        // Add CORS Filter
        .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
        // Custom Token based authentication based on the header previously given to the client
        .addFilterBefore(new VerifyTokenFilter(tokenUtil), UsernamePasswordAuthenticationFilter.class)
        // custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
        .addFilterBefore(new GenerateTokenForUserFilter ("/session", authenticationManager(), tokenUtil), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
