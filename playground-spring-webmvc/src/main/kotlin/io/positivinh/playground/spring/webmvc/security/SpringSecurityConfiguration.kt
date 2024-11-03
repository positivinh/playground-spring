package io.positivinh.playground.spring.webmvc.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * Spring security configuration
 *
 * See [Reference documentation](https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html)
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class SpringSecurityConfiguration {

    /**
     * Security filter chain configuration
     *
     * See [Reference documentation](https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html)
     * See [Actuator security configuration](https://docs.spring.io/spring-boot/reference/actuator/endpoints.html#actuator.endpoints.security)
     *
     */
    @Bean
    fun securityFilterChain(http: HttpSecurity, authorizationTokenFilter: AuthorizationTokenFilter): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeHttpRequests {
                authorize("/actuator/**", permitAll) // https://docs.spring.io/spring-boot/reference/actuator/endpoints.html#actuator.endpoints.security
                authorize(anyRequest, authenticated)
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(authorizationTokenFilter)
        }

        return http.build()
    }

    /**
     * Cors configuration
     *
     * See [Reference documentation](https://docs.spring.io/spring-security/reference/reactive/integrations/cors.html)
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {

        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.allowedMethods = listOf("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }
}
