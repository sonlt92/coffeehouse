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
//                //C???u h??nh cho c??c ??u??ng d???n kh??ng c???n x??c th???c
//                .antMatchers("/", "/login", "/register").permitAll()
////                //C???u h??nh cho c??c ???????ng d???n ????ng nh???p b???ng Role l?? Member, Admin
////                .antMatchers("/staff/**").hasAnyRole("STAFF", "MANAGER")
////                //c???u h??nh cho ???????ng d???n admin, ch??? c?? Role admin m???i v??o ???????c
////                .antMatchers("/manager/**").hasRole("MANAGER")
//                .and()
//                //formlogin
//                .formLogin()
//                //???????ng d???n tr??? v??? trang authentication
//                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                //N???u authentication th??nh c??ng
//                .defaultSuccessUrl("/")
//                //N???u authentication th???t b???i
//                .failureUrl("/login?error")
//                //N???u authentication th??nh c??ng nh??ng v??o trang kh??ng ????ng role
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
                //C???u h??nh cho c??c ???????ng d???n ????ng nh???p b???ng Role l?? Member, Admin
                .antMatchers("/staff/**").hasAnyRole("STAFF", "MANAGER")
                //c???u h??nh cho ???????ng d???n admin, ch??? c?? Role admin m???i v??o ???????c
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
