package com.kkaczynski.assessment.repository;

import com.kkaczynski.assessment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
