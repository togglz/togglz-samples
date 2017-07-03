package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

@SpringBootApplication
public class Application {

    @SuppressWarnings("unchecked")
	@Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(MyFeatures.class);
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests()
                        .anyRequest()
                        .authenticated()
                        .and()
                    .csrf()
                        .disable()
                    .formLogin()
                        .and()
                    .logout();
            // @@formatter:on
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            // @formatter:off
            auth.inMemoryAuthentication()
                    .withUser("admin")
                        .password("pwd")
                        .roles("ADMIN", "USER")
                        .and()
                    .withUser("user1")
                        .password("pwd")
                        .roles("USER")
                        .and()
                    .withUser("user2")
                        .password("pwd")
                        .roles("USER")
                        .and()
                    .withUser("user3")
                        .password("pwd")
                        .roles("USER")
                        .and()
                    .withUser("user4")
                        .password("pwd")
                        .roles("USER");
            // @formatter:on
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
