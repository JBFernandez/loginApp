package com.example.loginAPI;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean { //class que hereda de GenericFilterBean, al ponerlo solicita implementar los métodos y damos ok
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request; //hace un cast de ServletRequest a HttpServletRequest -> Objeto con opciones Http
        HttpServletResponse httpServletResponse = (HttpServletResponse) response; // hacer también el cast

        String authHeader = httpServletRequest.getHeader("authorization"); //header que ponemos en postman o javascript

        if ( (httpServletRequest.getMethod().equals("POST")) ||
                (httpServletRequest.getMethod().equals("PUT")) ||
                (httpServletRequest.getMethod().equals("DELETE")) ||
                httpServletRequest.getMethod().equals("GET")  ) {
            if (  (authHeader == null) || (! authHeader.startsWith("Bearer ")) ) { // OJO: TENDRIAMOS QUE AGREGAR BEARER EN POSTMAN Y JAVASCRIPT PARA VALIDAR
                throw new ServletException("1. Invalid Token!"); //A servlet is a class that is used to extend the capabilities of servers that host applications by means of a request-response model.
            } //if con header null o sin Bearer
            String token = authHeader.substring(7); //lo que viene después de Bearer_ (osea el token)
            try {
                Claims claims = Jwts.parser().setSigningKey("Here-goes-the-secret-key") //claims are pieces of information, an ID token (which is always a JWT) can contain a claim called name
                        .parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
            }//try
            catch (SignatureException | MalformedJwtException e) {
                throw new ServletException("2. Invalid Token");
            }//catch
        } //if POST - PUT-GET - DELETE
         chain.doFilter(httpServletRequest, httpServletResponse);
    }//doFilter

}//class Jwt
