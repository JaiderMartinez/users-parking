package co.com.user.r2dbc;

import co.com.user.model.user.User;
import co.com.user.model.user.gateways.UserGateway;
import co.com.user.r2dbc.dao.UserDao;
import co.com.user.r2dbc.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserGateway {

    private final UserDao userDao;

    @Override
    public Mono<User> save(User user) {
        return userDao.save(UserMapper.toEntity(user))
                .map(UserMapper::toModel);
    }

    @Override
    public Mono<User> findById(Long idUser) {
        return userDao.findById(idUser)
                .map(UserMapper::toModel);
    }
}
