//package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.configuration;
//
//
//import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service.MyUserDetaiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
////@Configuration
////@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private MyUserDetaiService myuserDetailsService;
//
//
//    protected void configure(AuthenticationManagerBuilder auth )throws Exception{
//        auth.userDetailsService(myuserDetailsService);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(customiser->customiser.disable())
//                .authorizeHttpRequests(request->request.requestMatchers("register","login","authenticate").permitAll()
//                        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
//                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
//
//        // Add the JWT filter before UsernamePasswordAuthenticationFilter
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return httpSecurity.build();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//
//
////    private final JwtRequestFilter jwtRequestFilter;
////
////    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
////        this.jwtRequestFilter = jwtRequestFilter;
////    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests()
////                .requestMatchers("/customer/login", "/customer/register").permitAll() // Allow login and register
////                .anyRequest().authenticated()  // Require authentication for all other requests
////                .and()
////                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
////        return http.build();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
