package co.com.user.model.user.gateways;

import co.com.user.model.user.config.ErrorDictionary;
import reactor.core.publisher.Mono;

public interface ErrorDictionaryGateway {

    Mono<ErrorDictionary> findById(String id);
}
