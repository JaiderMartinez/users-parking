package co.com.user.config.security;

import co.com.user.model.user.config.ErrorCode;
import co.com.user.model.user.config.ParkingException;
import co.com.user.utils.Constant;
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
                            userAuth,
                            auth.getCredentials(),
                            userAuth.getGroups()
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList()
                    );
                })
                .onErrorResume(throwable -> {
                    log.error(Constant.LOG_ERROR_AUTHENTICATE_USER, throwable.getMessage());
                    return Mono.error(new ParkingException(ErrorCode.B401000));
                });
    }
}
