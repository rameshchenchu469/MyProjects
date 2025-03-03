
  package com.nt.config;
  
  import java.util.Date;
  
  import javax.crypto.SecretKey;
  
  import org.springframework.security.core.Authentication; import
  org.springframework.stereotype.Service;
  
  import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwt; import
  io.jsonwebtoken.Jwts; import io.jsonwebtoken.security.Keys;
  
  @Service public class JwtProvider {
  
  SecretKey key =Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
  
  public String generateToken(Authentication auth) { String jwt =
  Jwts.builder() .issuedAt(new Date()) .setExpiration(new Date(new
  Date().getTime()+84600000)) .claim("email",auth.getName())
  .signWith(key).compact(); return jwt; }
  
  public String getEmailFromToken(String jwt) { jwt = jwt.substring(7);
  
  Claims claims =
  Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
  
  String email = String.valueOf(claims.get("email"));
  
  return email; } }
 