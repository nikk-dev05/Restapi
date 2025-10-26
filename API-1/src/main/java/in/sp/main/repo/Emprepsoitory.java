package in.sp.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.Employee;

public interface Emprepsoitory extends JpaRepository<Employee, Integer> {

}
