package com.example.loginAPI.controller;

import com.example.loginAPI.UsersRepository;
import com.example.loginAPI.model.LoginData;
import com.example.loginAPI.model.Token;
import com.example.loginAPI.model.Users;
import com.example.loginAPI.utils.SHAUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class AuthController {
    private final UsersRepository usersRepository;//***SEGUN YO FALTA EL AUTOWIRED **

    //@Autowired
    public AuthController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }//constructor

    @PostMapping //
    public Token login(@RequestBody LoginData data) throws ServletException { //En el body del request pedimos un objeto de la clase LoginData
        Optional<Users> userByEmail = usersRepository.findByEmail(data.getEmail());
        if (userByEmail.isPresent()) {
            if (SHAUtil.verifyHash(data.getPassword(), userByEmail.get().getPassword())) { //Como el método verifyHash de SHAUtil es static no lo tengo que instanciar
                 return new Token(generateToken(data.getEmail()));                                                         //El get adicional es por el Optional
            }//if verifyhash
        }//if email isPresent
        throw new ServletException("Invalid login. Please check your credentials");
    }//login()

    private String generateToken(String email) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 10); // a la variable calendar (que tiene la fecha y hora actual) le agregamos 10 horas (vigencia del Token)
        String secret = "Here-goes-the-secret-key";

        return Jwts.builder().setSubject(email).claim("role", "user").setIssuedAt(new Date()).setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }//generateToken()
}//Class AuthController
