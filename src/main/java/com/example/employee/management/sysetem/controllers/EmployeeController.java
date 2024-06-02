package com.example.employee.management.sysetem.controllers;

import com.example.employee.management.sysetem.entitys.Department;
import com.example.employee.management.sysetem.entitys.Employee;
import com.example.employee.management.sysetem.projection.EmployeeProjection;
import com.example.employee.management.sysetem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

//    endpoint to get an employee by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProjection> getEmployeeById(@PathVariable Long id) throws Exception {
        return employeeService.getEmployee(id);
    }
//    endpoint to get all employees
    @GetMapping
    public List<EmployeeProjection> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

//    endpoint to adding new employee
    @PostMapping
    public Employee addingNewEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

//    update employee data
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProjection> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeData) throws Exception {
        EmployeeProjection updatedEmployee =employeeService.updateEmployeeData(id, employeeData);
        return ResponseEntity.ok(updatedEmployee);
    }

//    delete existing Employee by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

//    assign department to employee
    @PutMapping("/{employeeId}/department/{departmentId}")
    public Employee assignDepartmentToEmployee(@PathVariable(name = "employeeId") Long employeeId, @PathVariable(name = "departmentId") Long departmentId) throws Exception {
        return employeeService.assignDepartmentToEmployee(employeeId, departmentId);
    }

}
