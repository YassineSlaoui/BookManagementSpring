package com.ys.bookmanagementspring.conf;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("user_admin").password("{noop}1234").roles(UserRole.ADMIN.name()).build();
        UserDetails publisher = User.withUsername("user_publisher").password("{noop}123").roles(UserRole.PUBLISHER.name()).build();
        UserDetails readonly = User.withUsername("user_readonly").password("{noop}12").roles(UserRole.READ_ONLY.name()).build();
        return new InMemoryUserDetailsManager(admin, publisher, readonly);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/edit/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.PUBLISHER.name())
                        .requestMatchers("/delete/**").hasAnyRole(UserRole.ADMIN.name())
                        .requestMatchers("/api/**").hasAnyRole(UserRole.ADMIN.name())
//                        .requestMatchers("/actuator/**").hasAnyRole(UserRole.ADMIN.name())
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).hasAnyRole(UserRole.ADMIN.name())
                        .anyRequest().authenticated())
                .formLogin(withDefaults());

        return http.build();
    }

}
