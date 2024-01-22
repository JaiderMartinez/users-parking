package co.com.user.config.security;

import co.com.user.model.user.config.ErrorCode;
import co.com.user.model.user.config.ParkingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthenticatorManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map( auth -> {
                    User userAuth = JwtProviderUtil.getPayload(auth.getCredentials().toString());
                    return (Authentication) new UsernamePasswordAuthenticationToken(
                            userAuth.getEmail(),
                            null,
                            userAuth.getGroups()
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList()
                    );
                })
                .onErrorResume(throwable -> {
                    log.error("Error al autenticar usuario: {}", throwable.getMessage());
                    return Mono.error(new ParkingException(ErrorCode.B401000));
                });
    }
}
