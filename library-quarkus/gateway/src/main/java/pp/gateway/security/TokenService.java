package pp.gateway.security;


import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logmanager.Logger;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import javax.enterprise.context.RequestScoped;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;


@RequestScoped
public class TokenService {

    public final static Logger LOGGER = Logger.getLogger(TokenService.class.getSimpleName());

    public String generateUserToken(String username) {
        return generateToken(username, "ROLE_USER");
    }

    public String generateToken(String username, String role) {
        try {
            JwtClaims jwtClaims = new JwtClaims();
            jwtClaims.setIssuer("userpp");
            jwtClaims.setSubject(username);
            jwtClaims.setClaim(Claims.groups.name(), Collections.singletonList(role));
            jwtClaims.setExpirationTimeMinutesInTheFuture(60);

            String token = TokenUtils.generateTokenString(jwtClaims);
            LOGGER.info("TOKEN generated: " + token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public JwtClaims decodeToken(String jwt) {
        JwtClaims jwtClaims = new JwtClaims();
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setSkipAllValidators()
                .setDisableRequireSignature()
                .setSkipSignatureVerification()
                .build();
        try {
            jwtClaims = jwtConsumer.processToClaims(jwt);
        } catch (InvalidJwtException e) {
            e.printStackTrace();
        }
        return jwtClaims;
    }

    public static boolean checkIfTokenExpired(JwtClaims jwtClaims){
        long expDate = (long) jwtClaims.getClaimsMap().get("exp");
        Date date = new Date();
        if(expDate* 1000 < date.getTime()){
            return true;
        } else {
            return false;
        }
    }


}
