package co.com.user.r2dbc;

import co.com.user.model.user.config.ErrorDictionary;
import co.com.user.model.user.gateways.ErrorDictionaryGateway;
import co.com.user.r2dbc.dao.ErrorDictionaryDao;
import co.com.user.r2dbc.mapper.ErrorDictionaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ErrorDictionaryRepositoryImpl implements ErrorDictionaryGateway {

    private final ErrorDictionaryDao errorDictionaryDao;

    @Override
    public Mono<ErrorDictionary> findById(String id) {
        return errorDictionaryDao.findById(id)
                .map(ErrorDictionaryMapper::toModel);
    }
}
