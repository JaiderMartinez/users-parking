package co.com.user.config;

import co.com.user.model.user.gateways.ErrorDictionaryGateway;
import co.com.user.model.user.gateways.UserGateway;
import co.com.user.usecase.ErrorDictionaryUseCase;
import co.com.user.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*@ComponentScan(basePackages = "co.com.user.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)*/
public class UseCasesConfig {

        @Bean
        public UserUseCase userUseCase(UserGateway userGateway) {
                return new UserUseCase(userGateway);
        }

        @Bean
        public ErrorDictionaryUseCase errorDictionaryUseCase(ErrorDictionaryGateway errorDictionaryGateway) {
                return new ErrorDictionaryUseCase(errorDictionaryGateway);
        }
}
