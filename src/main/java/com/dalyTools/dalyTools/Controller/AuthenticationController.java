package com.dalyTools.dalyTools.Controller;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Service.PersonService;
import com.dalyTools.dalyTools.DAO.dto.AuthenticationRequestDto;
import com.dalyTools.dalyTools.DAO.dto.JwtAuthDto;
import com.dalyTools.dalyTools.Securityty.JwtTokenProvider;
import com.dalyTools.dalyTools.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private PersonService personService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("login")
    public ResponseEntity<JwtAuthDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        try {
            Person person = personService.findByUserName(authenticationRequestDto.getUsername()).orElseThrow(
                    ()-> new NotFoundException("Invalid password or username"));
            String accessToken = jwtTokenProvider.createAccessToken(person);
            String refreshToken = jwtTokenProvider.createRefreshToken(person.getId());


            jwtAuthDto.setUsername(authenticationRequestDto.getUsername());
            jwtAuthDto.setAccessToken(accessToken);
            jwtAuthDto.setRefreshToken(refreshToken);


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(jwtAuthDto);

    }
    @GetMapping("/activate/{activationCode}")
    public ResponseEntity activateUser(@PathVariable String activationCode) {
        personService.activateUser(activationCode);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
