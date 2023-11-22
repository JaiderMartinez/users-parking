package co.com.user.usecase;

import co.com.user.model.user.User;
import co.com.user.model.user.config.ErrorCode;
import co.com.user.model.user.config.ParkingException;
import co.com.user.model.user.gateways.UserGateway;
import co.com.user.usecase.utils.ValueUpdaterUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;

    public Mono<User> save(User user) {
        return userGateway.save(user);
    }

    public Mono<User> findById(Long idUser) {
        return userGateway.findById(idUser)
                .switchIfEmpty(Mono.error(new ParkingException(ErrorCode.N000000)));
    }

    public Mono<User> update(User user, Long idUser) {
        return userGateway.findById(idUser)
                .switchIfEmpty(Mono.error(new ParkingException(ErrorCode.N000000)))
                .flatMap( userFound ->
                    userGateway.save(replaceValues(user, userFound))
                );
    }

    private User replaceValues(User userToUpdate, User userActual) {
        return userActual.toBuilder()
                .firstName(ValueUpdaterUtil.returnValueToUpdate(userActual.getFirstName(), userToUpdate.getFirstName()))
                .lastName(ValueUpdaterUtil.returnValueToUpdate(userActual.getLastName(), userToUpdate.getLastName()))
                .document(ValueUpdaterUtil.returnValueToUpdate(userActual.getDocument(), userToUpdate.getDocument()))
                .email(ValueUpdaterUtil.returnValueToUpdate(userActual.getEmail(), userToUpdate.getEmail()))
                .phone(ValueUpdaterUtil.returnValueToUpdate(userActual.getPhone(), userToUpdate.getPhone()))
                .password(ValueUpdaterUtil.returnValueToUpdate(userActual.getPassword(), userToUpdate.getPassword()))
                .locationX(ValueUpdaterUtil.returnValueToUpdate(userActual.getLocationX(), userToUpdate.getLocationX()))
                .locationY(ValueUpdaterUtil.returnValueToUpdate(userActual.getLocationY(), userToUpdate.getLocationY()))
                .build();
    }

    public Mono<User> updateUserCoordinates(User user, Long idUser) {
        return userGateway.findById(idUser)
                .switchIfEmpty(Mono.error(new ParkingException(ErrorCode.N000000)))
                .flatMap( userFound -> {
                    userFound.setLocationX(user.getLocationX());
                    userFound.setLocationY(user.getLocationY());
                    return userGateway.save(userFound);
                });
    }
}
