package com.cruiseline.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

/**
 * Returns a clean 401 JSON body for unauthenticated requests instead of the
 * default error page. The JSON is written by hand so this class does not
 * depend on any particular Jackson version (Spring Boot 4 ships Jackson 3).
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String body = """
                {"timestamp":"%s","status":401,"error":"Unauthorized",\
                "message":"Authentication is required to access this resource","path":"%s"}\
                """.formatted(Instant.now(), escape(request.getRequestURI()));

        response.getWriter().write(body);
    }

    /** Minimal JSON string escaping for the path value. */
    private String escape(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
