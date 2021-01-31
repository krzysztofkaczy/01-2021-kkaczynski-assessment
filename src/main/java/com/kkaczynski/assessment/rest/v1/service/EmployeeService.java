package com.kkaczynski.assessment.rest.v1.service;


import com.kkaczynski.assessment.entity.Employee;
import com.kkaczynski.assessment.rest.v1.model.ApiResponse;

import java.util.Optional;

public interface EmployeeService {
    ApiResponse.Status createEmployee(Employee employee);

    ApiResponse.Status deleteEmployee(Long employeeId);

    Optional<Employee> getEmployee(Long employeeId);

    ApiResponse.Status update(Employee employee);

}
