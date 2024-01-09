package co.com.user.config.security;

import co.com.user.model.user.config.ErrorCode;
import co.com.user.model.user.config.ParkingException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token == null || token.isBlank()) {
            return Mono.error(new ParkingException(ErrorCode.N401000));
        }
        exchange.getAttributes().put("token", token.replace("Bearer ", ""));
        return chain.filter(exchange);
    }
}
