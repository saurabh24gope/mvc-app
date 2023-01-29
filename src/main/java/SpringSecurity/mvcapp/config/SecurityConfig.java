package SpringSecurity.mvcapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService(){
//        UserDetails admin = User
//                .withUsername("Saurabh")
//                .password(passwordEncoder().encode("sau123"))
//                .roles("admin")
//                .build();
//        System.out.println("this is saurabh"+admin.getPassword());
//        UserDetails normalUser = User
//                .withUsername("Shilpi")
//                .password(passwordEncoder().encode("shi123"))
//                .roles("normalUser")
//                .build();
//        return new InMemoryUserDetailsManager(admin,normalUser);
          return  new UserInfoUserDetailsService();

    }
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/contact")
                .authenticated()
                .and()
                .formLogin().and().build();
    }
}
