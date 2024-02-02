package co.com.user.config.security;

import co.com.user.model.user.config.ErrorCode;
import co.com.user.model.user.config.ParkingException;
import co.com.user.utils.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticatorManager implements ReactiveAuthenticationManager {

    /**
     * Si falla dentro del just corta tod el flujo inclusive el onError
     * La regla es que dentro del just no puede llegar un throw se soluciona con un defer,
     * pasa cuando se consumen servicios no reactivos que no usan webclient
     * después del just pueden ocurrir todos los errores throw y mono.error que quieras
     * dentro del just no puede ir mono entonces no va a llegar un mono.error
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> {
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
