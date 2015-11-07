package by.keytrinket;

import by.keytrinket.config.KeyTrinketProperties;
import by.keytrinket.service.UserService;
import by.keytrinket.util.security.AuthRestEntyPoint;
import by.keytrinket.util.security.AuthSuccessEventHandler;
import by.keytrinket.util.security.UserDetails;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author ntishkevich
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "by.keytrinket.repository")
@EnableTransactionManagement
@EnableJpaAuditing
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(KeyTrinketProperties.class)
public class KeyTrinketApplication {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(KeyTrinketApplication.class, args);
    }

    @Bean
    protected PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    protected EhCacheFactoryBean ehCacheFactoryBean() {
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
        ehCacheFactoryBean.setCacheName("aclCache");
        return ehCacheFactoryBean;
    }

    @Bean
    protected EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        return new EhCacheManagerFactoryBean();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    protected AuditorAware auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return null;
            }
            return ((UserDetails) authentication.getPrincipal()).getUser();
        };
    }

    @Bean
    protected AuthenticationEntryPoint authRestEntryPoint() {
        return new AuthRestEntyPoint();
    }

    @Bean
    protected AuthSuccessEventHandler authSuccessEventHandler() {
        return new AuthSuccessEventHandler();
    }

    @Bean
    protected WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.exceptionHandling().authenticationEntryPoint(authRestEntryPoint())
                        .and().authorizeRequests().antMatchers("/api/**").authenticated()
                        .and().formLogin().successHandler(authSuccessEventHandler())
                        .and().logout().deleteCookies("jsessionid");
                http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400);
                http.csrf().disable().headers()
                        .frameOptions()
                        .sameOrigin();
            }
        };
    }

    @SuppressWarnings({"unchecked"})
    @Bean
    protected Jackson2ObjectMapperBuilder configureObjectMapper() {
        return new Jackson2ObjectMapperBuilder().modulesToInstall(Hibernate5Module.class);
    }
}
