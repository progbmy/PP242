package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;
import web.services.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Преобразование паролей в bcrypt
        authenticationProvider.setUserDetailsService(userDetailsService()); // Поиск по ключу и значению в базе пользователя
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()

                // указываем страницу с формой логина

//                .loginPage("/login")

                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())

//                .failureHandler(new loginFailureHandler())
                // указываем action с формы логина
//                .loginProcessingUrl("/perform_login")
                // Указываем параметры логина и пароля с формы логина
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .usernameParameter("username")
//                .passwordParameter("password")

                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();


                // делаем страницу регистрации недоступной для авторизированных пользователей
        http.authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
             //   .antMatchers("user/{id}/**").access("hasAnyRole('ADMIN','USER')")

//                .antMatchers("/users/**").hasAnyRole("ADMIN");

                .antMatchers("/users/**","/admin/**").access("hasAnyRole('ADMIN')").anyRequest().authenticated();

                //.antMatchers("/user").access("hasAnyRole('ADMIN','USER')").anyRequest().authenticated();
                // защищенные URL
                //.antMatchers("/hello").access("hasAnyRole('ADMIN')").anyRequest().authenticated();
//                .antMatchers("/user").access("hasAnyRole('ADMIN', 'USER')");

    }


}
