package com.kkaczynski.assessment.rest.v1.controllers;

import com.kkaczynski.assessment.entity.Employee;
import com.kkaczynski.assessment.rest.v1.model.ApiResponse;
import com.kkaczynski.assessment.rest.v1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value = "/employee")
public class EmployeeApi {

    private static final String INTERNAL_SYSTEM_ERROR = "Internal System Error";
    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    ResponseEntity<ApiResponse> createEmployee(@RequestBody Employee employee) {
        ApiResponse.Status status = employeeService.createEmployee(employee);
        return createResponse("Employee deleted", status, Optional.empty());
    }

    @DeleteMapping
    ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long employeeId) {
        ApiResponse.Status status = employeeService.deleteEmployee(employeeId);
        return createResponse("Employee deleted", status, Optional.empty());
    }

    @GetMapping
    ResponseEntity<ApiResponse> getEmployee(@PathVariable Long employeeId) {
        Optional<Employee> employeeOpt = employeeService.getEmployee(employeeId);
        ResponseEntity<ApiResponse> response;
        response = employeeOpt
                .map(employee -> ResponseEntity.ok(new ApiResponse(ApiResponse.Status.OK, null, employee)))
                .orElseGet(() -> new ResponseEntity<>(new ApiResponse(ApiResponse.Status.UNSUPPORTED_OPERATION,
                        "Not Found Employee"), HttpStatus.INTERNAL_SERVER_ERROR));
        return response;
    }

    @PutMapping
    ResponseEntity<ApiResponse> updateEmployee(@RequestBody Employee employee) {
        ApiResponse.Status status = employeeService.update(employee);
        ResponseEntity<ApiResponse> response = createResponse("Employee Updated", status, Optional.of(employee));
        return response;
    }

    private ResponseEntity<ApiResponse> createResponse(String message, ApiResponse.Status status, Optional<Object> payLoad) {
        ResponseEntity<ApiResponse> response;
        if (status == ApiResponse.Status.OK) {
            response = payLoad
                    .map(o -> new ResponseEntity<>(new ApiResponse(status, message, o), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new ApiResponse(status, message), HttpStatus.OK));
        } else {
            response = new ResponseEntity<>(new ApiResponse(status, INTERNAL_SYSTEM_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
