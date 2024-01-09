package co.com.user.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;

import java.util.Base64;

@UtilityClass
public class JwtProvider {

    public static User getPayload(String token) {
        String payload =token.split("\\.")[1];
        byte[] decodedPayloadBytes = Base64.getDecoder().decode(payload);
        String decodedPayload = new String(decodedPayloadBytes);
        ObjectMapper mapper = new ObjectMapperImp();
        return mapper.map(decodedPayload, User.class);
    }
}
