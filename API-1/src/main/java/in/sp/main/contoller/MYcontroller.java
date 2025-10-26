package in.sp.main.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entity.Employee;
import in.sp.main.service.Userservice;

@RestController
@RequestMapping("user")
public class MYcontroller {
	@Autowired
	private Userservice userservice;
     @PostMapping("/register")
	public ResponseEntity<?> creatuser(@RequestBody Employee employee){
		  if((employee.getEmail().isEmpty()|| employee.getEmail().isBlank()) || (employee.getPassword().isEmpty()||employee.getPassword().isBlank() )) {
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ALl filed must be fill compulsory!!!!");
			   }
		  
			 Employee emp = userservice.create_emp(employee);
			 return ResponseEntity.status(HttpStatus.CREATED).body(emp);
				  
	}
}
