package in.sp.main.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.sp.main.service.Jwtservice;
import in.sp.main.service.Userservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Jwtfilter extends OncePerRequestFilter {
	@Autowired
	private Jwtservice jwtservice;
	@Autowired
	private Userservice userservice;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	
		
		String authheader= request.getHeader("Authorization");
		String token=null;
		String username=null;
		if(authheader!=null && authheader.startsWith("Bearer")) {
			token=authheader.substring(7);
			username=jwtservice.extractUsername(token);
			
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userservice.loadUserByUsername(username);
			if(jwtservice.validateToken(token, userDetails)) {
   	    	 UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
   	    	 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
   	          
   	    	 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
   	     }
			
		}
		filterChain.doFilter(request, response);
	}
	
}
