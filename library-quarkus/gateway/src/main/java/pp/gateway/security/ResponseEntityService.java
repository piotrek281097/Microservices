package pp.gateway.security;

import org.jose4j.jwt.JwtClaims;
import pp.gateway.domain.UserData;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ResponseEntityService {

    public ResponseEntity checkUser(JwtClaims jwtClaims) {
        UserData existingUser = UserData.find("username", jwtClaims.getClaimsMap().get("sub")).firstResult();
        if (existingUser != null) {
            if (TokenService.checkIfTokenExpired(jwtClaims)) {
                return new ResponseEntity(false, "Token has expired.");
            } else {
                return new ResponseEntity(true, "Everything is ok.");
            }
        } else {
            return new ResponseEntity(false, "Something went wrong.");
        }
    }

    public ResponseEntity checkForUser(UserData userData) throws Exception {
        UserData existingUser = UserData.find("username", userData.getUsername()).firstResult();
        if(existingUser == null){
            return new ResponseEntity(false, "Account with this username is not registered.");
        } else {

            if(existingUser.getPassword().equals(userData.getPassword())) {
                return new ResponseEntity(true, "Everything is ok.");
            } else {
                return new ResponseEntity(false, "Bad password.");
            }
        }
    }
}
