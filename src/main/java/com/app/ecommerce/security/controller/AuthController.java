package com.app.ecommerce.security.controller;

import com.app.ecommerce.security.dto.JwtDto;
import com.app.ecommerce.security.dto.LoginUser;
import com.app.ecommerce.security.dto.Message;
import com.app.ecommerce.security.dto.NewUser;
import com.app.ecommerce.security.entity.User;
import com.app.ecommerce.security.entity.Rol;
import com.app.ecommerce.security.enums.RolName;
import com.app.ecommerce.security.jwt.JwtProvider;
import com.app.ecommerce.security.service.RolService;
import com.app.ecommerce.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RolService rolService;
    private final JwtProvider jwtProvider;



    @PostMapping("/create")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("wrongly set fields or invalid email"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity<>(new Message("the name already exists"), HttpStatus.BAD_REQUEST);

        User user =
                new User(newUser.getUsername(),
                        passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("user created"), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (!userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity<>(new Message("does not exist"), HttpStatus.NOT_FOUND);

        User user = userService.getOne(id).get();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userService.save(user);
        return new ResponseEntity<>(new Message("updated user"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Message("does not exist"), HttpStatus.NOT_FOUND);
        userService.delete(id);
        return new ResponseEntity<>(new Message("user deleted"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("wrongly set fields"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
