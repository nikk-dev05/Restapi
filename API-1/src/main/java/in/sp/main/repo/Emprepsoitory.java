package in.sp.main.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import in.sp.main.entity.Employee;

public interface Emprepsoitory extends JpaRepository<Employee, Integer> {
	Optional<Employee> findByUsername(String username);
}
