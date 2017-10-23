package com.bonespirito.kotlinflyway.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@Configuration
@EnableWebSecurity
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Autowired
    fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ROLE")
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .antMatchers("/categorias").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
    }

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources?.stateless(true)
    }
}