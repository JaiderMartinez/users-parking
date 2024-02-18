package co.com.user.config.security;

import co.com.user.model.user.config.ErrorCode;
import co.com.user.model.user.config.ParkingException;
import co.com.user.utils.Constant;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements WebFilter {

    @Override
    @NonNull
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        final String token = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);
        if (token == null || token.isBlank())
            return Mono.error(new ParkingException(ErrorCode.B401000));
        exchange.getAttributes()
                .put(Constant.KEY_TOKEN, token.replace(Constant.PREFIX_TOKEN, "").trim());
        return chain.filter(exchange);
    }
}
