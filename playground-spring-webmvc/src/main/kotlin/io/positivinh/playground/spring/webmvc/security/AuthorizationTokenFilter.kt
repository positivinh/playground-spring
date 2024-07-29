package io.positivinh.playground.spring.webmvc.security

import com.crabshue.commons.kotlin.logging.getLogger
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Primary
@Component
class AuthorizationTokenFilter : OncePerRequestFilter() {

    private val log = getLogger()

    companion object {

        val authorizedUsernameAndRoles = mapOf(
            "user" to listOf("ROLE_USER"),
            "vinh" to listOf("ROLE_USER", "ROLE_ADMIN")
        )
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val authorizationHeader = request.getHeader("Authorization")

        if (StringUtils.isBlank(authorizationHeader)) {
            filterChain.doFilter(request, response)
            return
        }

        val authorizedUser = StringUtils.normalizeSpace(authorizationHeader)

        log.debug("Authorization header value [{}]", authorizedUser)

        if (authorizedUsernameAndRoles.containsKey(authorizedUser)) {

            SecurityContextHolder.getContext()
                .authentication = UsernamePasswordAuthenticationToken(
                authorizedUser,
                null,
                authorizedUsernameAndRoles[authorizedUser]?.map { SimpleGrantedAuthority(it) }
            )
        }

        filterChain.doFilter(request, response)
    }
}
