package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import projecta07.jwt.JwtUtility;
import projecta07.model.User;
import projecta07.payload.JwtResponse;
import projecta07.payload.LoginRequest;
import projecta07.service.impl.UserService;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());


        final String token = jwtUtility.generateJwtToken(userDetails.getUsername());

        User user = userService.getUserByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(token, user.getIdUser(), user.getUsername(), user.getRoles()));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
