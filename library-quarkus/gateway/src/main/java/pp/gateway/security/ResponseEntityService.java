package pp.gateway.security;

import org.graalvm.compiler.lir.LIRInstruction;
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

//    public ResponseEntity checkForToken(String pin){
//        //        Calendar calendar = Calendar.getInstance();
////        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
////            System.out.println(verificationToken.getExpiryDate().getTime());
////            System.out.println(calendar.getTime().getTime());
////            return Response.status(500).entity("Token expired.").build();
////        }
//
//        VerificationToken verificationToken = VerificationToken.find("token", pin).firstResult();
//        if(verificationToken == null){
//            return new ResponseEntity(false, "Something went wrong...");
//        }
//        if(!(verificationToken.getToken().equals(pin))){
//            return new ResponseEntity(false, "Token is invalid.");
//        } else {
//            return new ResponseEntity(true, "Registration successful.");
//        }
//    }


    public ResponseEntity checkForUser(UserData userData){
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
