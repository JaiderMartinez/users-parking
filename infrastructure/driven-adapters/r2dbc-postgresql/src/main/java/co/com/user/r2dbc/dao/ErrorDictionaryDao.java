package co.com.user.r2dbc.dao;

import co.com.user.r2dbc.entities.ErrorDictionaryEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ErrorDictionaryDao extends R2dbcRepository<ErrorDictionaryEntity, String> {
}
