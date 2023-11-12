package co.com.user.r2dbc.dao;

import co.com.user.r2dbc.entities.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserDao extends R2dbcRepository<UserEntity, Long> {
}
