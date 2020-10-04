/*
 * Copyright 2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.core.security;

import io.jmix.core.CoreProperties;
import io.jmix.core.MessageTools;
import io.jmix.core.security.impl.InMemoryUserRepository;
import io.jmix.core.security.impl.SystemAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This security configuration can be used in test or simple projects, for example:
 * <pre>
 * &#64;SpringBootApplication
 * public class SampleApplication {
 *    // ...
 *
 *    &#64;EnableWebSecurity
 *    static class SecurityConfiguration extends CoreSecurityConfiguration {
 *
 *        &#64;Override
 *        public UserRepository userRepository() {
 * 	        InMemoryUserRepository repository = new InMemoryUserRepository();
* 	        repository.addUser(new CoreUser("admin", "{noop}admin", "Administrator"));
 * 	        return repository;
 *        }
 *    }
 * }
 * </pre>
 */
public class CoreSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CoreProperties coreProperties;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageTools messageTools;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new SystemAuthenticationProvider(userRepository()));

        CoreAuthenticationProvider userAuthenticationProvider = new CoreAuthenticationProvider(messageTools);
        userAuthenticationProvider.setUserDetailsService(userRepository());
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .anonymous().principal(userRepository().getAnonymousUser()).key(coreProperties.getAnonymousAuthenticationTokenKey())
                .and()
                .logout().logoutSuccessUrl("/#login")
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin();
    }

    @Bean(name = "core_authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "core_UserRepository")
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
}