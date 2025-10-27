package in.sp.main.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entity.Employee;
import in.sp.main.service.Jwtservice;
import in.sp.main.service.Userservice;

@RestController
@RequestMapping("user")
public class MYcontroller {
	@Autowired
	private Userservice userservice;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private Jwtservice jwtservice;
     @PostMapping("/register")
	public ResponseEntity<?> creatuser(@RequestBody Employee employee){
		  if((employee.getUsername().isEmpty()||employee.getUsername().isBlank())||(employee.getEmail().isEmpty()|| employee.getEmail().isBlank()) || (employee.getPassword().isEmpty()||employee.getPassword().isBlank() )) {
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ALl filed must be fill compulsory!!!!");
			   }
		  
			 Employee emp = userservice.create_emp(employee);
			 return ResponseEntity.status(HttpStatus.CREATED).body(emp);
				  
	}
     @PostMapping("/login")
     public String loginuser(@RequestBody Employee employee){
    	 Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getUsername(),employee.getPassword()));
 		
 		if(authentication.isAuthenticated()) {
 			return jwtservice.generateToken(employee.getUsername());
 		}
 		else {
 			throw new UsernameNotFoundException("Invalid credentials ");
 		}
     }
}
