package com.Remigiusz.MacronutrientsApiREST.Controllers;

import com.Remigiusz.MacronutrientsApiREST.DAO.Role;
import com.Remigiusz.MacronutrientsApiREST.DAO.RoleName;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception400_BAD_REQUEST;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Repository.RoleRepository;
import com.Remigiusz.MacronutrientsApiREST.Repository.UserRepository;
import com.Remigiusz.MacronutrientsApiREST.RequestAndRespone.JwtResponse;
import com.Remigiusz.MacronutrientsApiREST.RequestAndRespone.LoginForm;
import com.Remigiusz.MacronutrientsApiREST.RequestAndRespone.SignUpForm;
import com.Remigiusz.MacronutrientsApiREST.Security.JwtProvider;
import com.Remigiusz.MacronutrientsApiREST.Service.UserService;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
/*TESTTEST*/

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        long id=userRepository.findByUsername(userDetails.getUsername()).get().getId();

        return new JwtResponse(id,jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {



        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new Exception400_BAD_REQUEST("This username is already taken");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new Exception400_BAD_REQUEST("This email is already taken");
        }


        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles.contains("user"))
        {
            Role role = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow( () -> new Exception404_NOT_FOUND("ther is no role like this" ));

            roles.add(role);
        }

        if(strRoles.contains("admin"))
        {
            Role role = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow( () -> new Exception404_NOT_FOUND("ther is no role like this" ));

            roles.add(role);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new SimpleMessage("User registered successfully!"), HttpStatus.OK);
    }

}
