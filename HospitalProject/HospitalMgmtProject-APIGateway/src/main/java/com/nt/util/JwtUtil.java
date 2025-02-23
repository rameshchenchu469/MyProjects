//
//  package com.nt.util;
//  
//  import java.security.Key; 
//  import java.util.Date; 
//  import java.util.HashMap;
//  import java.util.Map;
//  
//  import org.springframework.stereotype.Component;
//  
//  import io.jsonwebtoken.Jwts; import io.jsonwebtoken.io.Decoders; import
//  io.jsonwebtoken.security.Keys;
//  
//  @Component 
//  public class JwtUtil {
//  
//  public static final String SECRET = "2197f290fafd6b84e0bf8876b4e08aabf7aa3fe33f5995cf6390d020f103f280";
//  
//  
//  private Key getSignKey() { 
//	byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//    return Keys.hmacShaKeyFor(keyBytes); 
//  }
//  
////  public void validateToken(final String token) {
////	  try {
////	        var claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
////	        System.out.println("Decoded claims: " + claims);
////	    } catch (io.jsonwebtoken.JwtException e) {
////	        throw new RuntimeException("Invalid token", e);
////	    }
////  
////  }
//  public void validateToken(final String token) {
//	    try {
//	        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//	    } catch (io.jsonwebtoken.ExpiredJwtException e) {
//	        throw new RuntimeException("Token has expired", e);
//	    } catch (io.jsonwebtoken.SignatureException e) {
//	        throw new RuntimeException("Invalid token signature", e);
//	    } catch (io.jsonwebtoken.JwtException e) {
//	        throw new RuntimeException("Token is invalid", e);
//	    }
//	}
//  
//  
//  }
// 