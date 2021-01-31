package com.kkaczynski.assessment.rest.v1.service.impl;

import com.kkaczynski.assessment.entity.Employee;
import com.kkaczynski.assessment.repository.EmployeeRepository;
import com.kkaczynski.assessment.rest.v1.model.ApiResponse;
import com.kkaczynski.assessment.rest.v1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ApiResponse.Status createEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return ApiResponse.Status.OK;
        } catch (Exception x) {
            return ApiResponse.Status.ERROR;
        }
    }

    @Override
    public ApiResponse.Status deleteEmployee(Long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
        } catch (Exception x) {
            return ApiResponse.Status.ERROR;
        }
        return ApiResponse.Status.OK;
    }

    @Override
    public Optional<Employee> getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public ApiResponse.Status update(Employee employee) {
        ApiResponse.Status status;
        try {
            employeeRepository.save(employee);
            status = ApiResponse.Status.OK;
        } catch (Exception x) {
            status = ApiResponse.Status.ERROR;
        }
        return status;
    }

}
