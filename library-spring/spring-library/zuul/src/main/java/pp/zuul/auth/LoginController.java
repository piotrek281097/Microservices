package pp.zuul.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pp.zuul.dto.JwtDto;
import pp.zuul.dto.UserDto;
import pp.zuul.security.jwt.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class LoginController {

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtTokenUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public JwtDto login(@RequestBody UserDto user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetailsImpl.getAuthorities().stream().map(GrantedAuthority::getAuthority
        ).collect(Collectors.toList());
        return new JwtDto(jwt, roles);

    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> register(@RequestBody User user) throws RoleNotFoundException {
//        return userServices.register(user);
//    }
}
