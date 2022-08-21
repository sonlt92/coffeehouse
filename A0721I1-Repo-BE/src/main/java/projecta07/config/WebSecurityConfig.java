package projecta07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import projecta07.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and()
//                .authorizeRequests()
//                //Cấu hình cho các đuòng dẫn không cần xác thực
//                .antMatchers("/", "/login", "/register").permitAll()
////                //Cấu hình cho các đường dẫn đăng nhập bằng Role là Member, Admin
////                .antMatchers("/staff/**").hasAnyRole("STAFF", "MANAGER")
////                //cấu hình cho đường dẫn admin, chỉ có Role admin mới vào được
////                .antMatchers("/manager/**").hasRole("MANAGER")
//                .and()
//                //formlogin
//                .formLogin()
//                //Đường dẫn trả về trang authentication
//                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                //Nếu authentication thành công
//                .defaultSuccessUrl("/")
//                //Nếu authentication thất bại
//                .failureUrl("/login?error")
//                //Nếu authentication thành công nhưng vào trang không đúng role
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/403")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/").permitAll()
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")

        http.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/authenticate", "/register","/**").permitAll()
                //Cấu hình cho các đường dẫn đăng nhập bằng Role là Member, Admin
                .antMatchers("/staff/**").hasAnyRole("STAFF", "MANAGER")
                //cấu hình cho đường dẫn admin, chỉ có Role admin mới vào được
                .antMatchers("/manager/**").hasRole("MANAGER")
                // all other requests need to be authenticated
                .anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ;
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }


}
