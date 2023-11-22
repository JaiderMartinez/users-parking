package co.com.user.usecase;

import co.com.user.model.user.config.ErrorDictionary;
import co.com.user.model.user.gateways.ErrorDictionaryGateway;
import reactor.core.publisher.Mono;

public class ErrorDictionaryUseCase {

    private final ErrorDictionaryGateway errorDictionaryGateway;

    public ErrorDictionaryUseCase(ErrorDictionaryGateway errorDictionaryGateway) {
        this.errorDictionaryGateway = errorDictionaryGateway;
    }

    public Mono<ErrorDictionary> findById(String id) {
        return errorDictionaryGateway.findById(id);
    }
}
