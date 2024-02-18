package co.com.user.model.user.gateways;

import co.com.user.model.user.User;
import reactor.core.publisher.Mono;

public interface UserGateway {

    Mono<User> save(User user);

    Mono<User> findById(Long idUser);
}
