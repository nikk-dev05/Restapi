package in.sp.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Empinfo;
import in.sp.main.entity.Employee;
import in.sp.main.repo.Emprepsoitory;


@Service
public class Userservice implements UserDetailsService {
	@Autowired
	private Emprepsoitory emprepsoitory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> emp=emprepsoitory.findByUsername(username);
		if(!emp.isPresent()) {
			throw new UsernameNotFoundException("user not found !!!!!!!!");
		}
	
		return  new Empinfo(emp.get());
	}
   public Employee create_emp(Employee employee) {
	  employee.setPassword(passwordEncoder.encode(employee.getPassword()));
	   return  emprepsoitory.save(employee) ;
   }
}
