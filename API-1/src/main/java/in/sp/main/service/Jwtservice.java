package in.sp.main.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class Jwtservice {

	private static final String SECRET = "210247923yrhf89ry3u0ejhgf38yuohfvgy38uwjdog3y8u9wojf!@##$$%%%%%%%^^^&&&*****";
	public String generateToken(String username) {
		Map<String , Object> claims= new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
	}
	public Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				 .setSigningKey(getSignKey())
				 .build()
				 .parseClaimsJws(token)
				 .getBody();
	}
	public String extractUsername(String token) {
	
		return extractAllClaims(token).getSubject();
	 }
	

	 public Date extractDate(String token) {
		 return extractAllClaims(token).getExpiration();
	 }
	 private Boolean isTokenExpired(String token) {
		 return extractDate(token).before(new Date());
	 }
	 public Boolean validateToken(String token,UserDetails userDetails) {
		  final String username=extractUsername(token);
		  return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	 }

}
