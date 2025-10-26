package in.sp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Employee;
import in.sp.main.repo.Emprepsoitory;

@Service
public class Userservice {
	@Autowired
	private Emprepsoitory emprepsoitory;
   public Employee create_emp(Employee employee) {
	   
	   return  emprepsoitory.save(employee) ;
   }
}
