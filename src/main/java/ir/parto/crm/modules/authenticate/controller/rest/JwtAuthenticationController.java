package ir.parto.crm.modules.authenticate.controller.rest;

import ir.parto.crm.modules.authenticate.model.service.*;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/login")
public class JwtAuthenticationController implements RestControllerInterface {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest loginRequest) throws Exception {
        System.out.println("-----------con-------------"+  loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        System.out.println("-----------con--------1-----"+  loginRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new ResponseToken(jwt,"success").getResponse());
    }

//    @RequestMapping(value = "/signup" , method = RequestMethod.POST)
//    public ResponseEntity<?> registerUser(@RequestBody JwtRequest signUpRequest) {
//        if(userDetailsService.existsByUsername(signUpRequest.getUsername())) {
//            return new ResponseEntity(new ApiResponse("error", 0, new ArrayList(Arrays.asList("Username is already taken!"))).getFaultResponse(),
//                    HttpStatus.BAD_REQUEST);
//        }
//        if(signUpRequest.getPassword().length() <32){
//            return new ResponseEntity(new ApiResponse("error", 1, new ArrayList(Arrays.asList("Password is not Secure!"))).getFaultResponse(),
//                    HttpStatus.BAD_REQUEST);
//        }
//        // Creating user's account
//        Admin admin = new Admin(signUpRequest.getUsername(), signUpRequest.getPassword());
//
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//        Admin result = userDetailsService.save(admin);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/api/users/{username}")
//                .buildAndExpand(result.getUsername()).toUri();
//        Map map = new HashMap<>();
//        map.put("user",admin);
//        return ResponseEntity.ok(new ApiResponse("success", new ArrayList(Arrays.asList(map))).getSuccessResponse());
//    }

    private void authenticate(String username, String password) throws Exception {
        try {
            System.out.println("-----------99--");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,  passwordEncoder.encode(password)));
            System.out.println("-----------100--");
        } catch (DisabledException e) {
            System.out.println("-----------101--");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {

            System.out.println((password)+"-----------102--"+e.getMessage()+"----------------"+username+"----"+password);
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



}

