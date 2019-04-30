package com.Remigiusz.MacronutrientsApiREST.Controllers;

import com.Remigiusz.MacronutrientsApiREST.DAO.DayORM;
import com.Remigiusz.MacronutrientsApiREST.DAO.Role;
import com.Remigiusz.MacronutrientsApiREST.DAO.RoleName;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import com.Remigiusz.MacronutrientsApiREST.Repository.RoleRepository;
import com.Remigiusz.MacronutrientsApiREST.Repository.UserRepository;
import com.Remigiusz.MacronutrientsApiREST.Security.JwtProvider;
import com.Remigiusz.MacronutrientsApiREST.Service.UserService;
import com.Remigiusz.MacronutrientsApiREST.message.request.LoginForm;
import com.Remigiusz.MacronutrientsApiREST.message.request.SignUpForm;
import com.Remigiusz.MacronutrientsApiREST.message.response.JwtResponse;
import com.Remigiusz.MacronutrientsApiREST.message.response.ResponseMessage;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;

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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {



        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }



    /*@PostMapping("/register")
    void registration(@RequestBody UserORM userORM)
    {
        userService.saveUser(userORM);
    }

    @PostMapping("/signIn")
    UserORM registration(@RequestBody JSONObject jsonObject)
    {
        UserORM userORM=userService.authentication(jsonObject.getAsString("password"),jsonObject.getAsString("email"));
        return userORM;
    }
    @GetMapping("/user/{id}")
    UserORM getUser(@PathVariable int id)
    {
       return userService.findUserById(id);
    }*/

}
