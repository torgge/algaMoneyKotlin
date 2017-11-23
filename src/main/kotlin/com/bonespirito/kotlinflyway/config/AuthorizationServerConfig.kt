package com.bonespirito.kotlinflyway.config

import com.bonespirito.kotlinflyway.config.token.CustomTokenEnhancer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import java.util.*

@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.inMemory()
                .withClient("angular")
                .secret("angular")
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(3600 * 24)
            .and()
                .withClient("mobile")
                .secret("mobile")
                .scopes("read")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(3600 * 24)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        val tokenEnhancerChain = TokenEnhancerChain()
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()))
        endpoints!!
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                //.accessTokenConverter(accessTokenConverter())
                .reuseRefreshTokens(false)
                .authenticationManager(authenticationManager)
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter? {
        val accessTokenConverter = JwtAccessTokenConverter()
        accessTokenConverter.setSigningKey("algaworks")
        return accessTokenConverter
    }

    @Bean
    fun tokenStore(): TokenStore? = JwtTokenStore(accessTokenConverter())

    @Bean
    fun tokenEnhancer(): TokenEnhancer {
        return CustomTokenEnhancer()
    }
}