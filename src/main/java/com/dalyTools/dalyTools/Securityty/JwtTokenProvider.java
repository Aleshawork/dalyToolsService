package com.dalyTools.dalyTools.Securityty;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Entity.RefreshToken;
import com.dalyTools.dalyTools.DAO.Repository.RefreshTokenRepository;
import com.dalyTools.dalyTools.DAO.Service.PersonService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLData;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtTokenProvider {
    private Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.access}")
    private Long accessTokenExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenExpiration;

    private JwtUserDatailService jwtUserDatailService;
    private PersonService personService;
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public JwtTokenProvider(JwtUserDatailService jwtUserDatailService, PersonService personService, RefreshTokenRepository refreshTokenRepository) {
        this.jwtUserDatailService = jwtUserDatailService;
        this.personService = personService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createAccessToken(Person person){
        Claims claims = Jwts.claims().setSubject(person.getUsername());
        claims.put("role", person.getRole().getName());
//        claims.put("id",person.getId());
//        claims.put("name",person.getName());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpiration);

        logger.info("Created access JWT for user " + person.toString());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String createRefreshToken(long userID) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refreshTokenExpiration);

        UUID randUUID = UUID.randomUUID();

        String createdToken = Jwts.builder()
                .setSubject(String.valueOf(randUUID))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();


        refreshTokenRepository.save(new RefreshToken(userID, createdToken));
        return createdToken;
    }


    public String resolveAccessToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateAccessToken(String token) {
        try {
            // вытаскиваем из JWT токена информацию
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Date now =new Date();
            return !claims.getBody().getExpiration().before(new Date());
            //getExpiration().before(new Date())
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = jwtUserDatailService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }



}
