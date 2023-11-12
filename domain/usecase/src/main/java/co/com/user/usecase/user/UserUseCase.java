package co.com.user.usecase.user;

import co.com.user.model.user.User;
import co.com.user.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;

    public Mono<User> save(User user) {
        return userGateway.save(user);
    }

    public Mono<User> findById(Long idUser) {
        return userGateway.findById(idUser);
    }

    public Mono<User> update(User user, Long idUser) {
        return userGateway.findById(idUser)
                .flatMap( userFound ->
                    userGateway.save(replaceValues(user, userFound))
                );
    }

    private User replaceValues(User userToUpdate, User userActual) {
        userActual.setFirstName(returnValueToUpdate(userActual.getFirstName(), userToUpdate.getFirstName()));
        userActual.setLastName(returnValueToUpdate(userActual.getLastName(), userToUpdate.getLastName()));
        userActual.setDocument(returnValueToUpdate(userActual.getDocument(), userToUpdate.getDocument()));
        userActual.setEmail(returnValueToUpdate(userActual.getEmail(), userToUpdate.getEmail()));
        userActual.setPhone(returnValueToUpdate(userActual.getPhone(), userToUpdate.getPhone()));
        userActual.setPassword(returnValueToUpdate(userActual.getPassword(), userToUpdate.getPassword()));
        userActual.setLocationX(returnValueToUpdate(userActual.getLocationX(), userToUpdate.getLocationX()));
        userActual.setLocationY(returnValueToUpdate(userActual.getLocationY(), userToUpdate.getLocationY()));
        return userActual;
    }

    private String returnValueToUpdate(String valueActual, String valueToUpdate) {
        return valueToUpdate == null ?
                valueActual : valueToUpdate;
    }

    public Mono<User> updateUserCoordinates(User user, Long idUser) {
        return userGateway.findById(idUser)
                .flatMap( userFound -> {
                    userFound.setLocationX(user.getLocationX());
                    userFound.setLocationY(user.getLocationY());
                    return userGateway.save(userFound);
                });
    }
}
