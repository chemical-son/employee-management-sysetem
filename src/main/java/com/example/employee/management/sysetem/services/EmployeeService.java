package com.example.employee.management.sysetem.services;


import com.example.employee.management.sysetem.entitys.Department;
import com.example.employee.management.sysetem.entitys.Employee;
import com.example.employee.management.sysetem.projection.EmployeeProjection;
import com.example.employee.management.sysetem.repositores.DepartmentRepository;
import com.example.employee.management.sysetem.repositores.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

//    get a specific employee by its id
    public ResponseEntity<EmployeeProjection> getEmployee(Long id) {
       EmployeeProjection employee = employeeRepository.findEmployeeById(id);
        return ResponseEntity.ok().body(employee);
    }


//    get all employees
    public List<EmployeeProjection> getAllEmployees(){
        return employeeRepository.findAllEmployeesWithDepartmentAndManager();
    }

//    adding new employee
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

//    update employee data
    @Transactional
    public EmployeeProjection updateEmployeeData(Long id, Employee updatedEmployee) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new Exception("This employee doesn't exist with id: " + id));


        employee.setFullName(updatedEmployee.getFullName() != null?updatedEmployee.getFullName(): employee.getFullName());
        employee.setAddress(updatedEmployee.getAddress() != null? updatedEmployee.getAddress() : employee.getAddress());
        employee.setDateOfBirth(updatedEmployee.getDateOfBirth() != null ? updatedEmployee.getDateOfBirth(): employee.getDateOfBirth());
        employee.setSalary(updatedEmployee.getSalary() != null ? updatedEmployee.getSalary() : employee.getSalary());
        employee.setDepartment(updatedEmployee.getDepartment() != null ? updatedEmployee.getDepartment(): employee.getDepartment());
        employeeRepository.save(employee);

        EmployeeProjection responseEmployee = employeeRepository.findEmployeeById(id);

        return responseEmployee;
    }

//    delete existing employee
    public void deleteEmployee(Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new Exception("This employee doesn't exist with id: " + id));
        employeeRepository.delete(employee);
    }

//    assign department to employee
    public Employee assignDepartmentToEmployee(Long employeeId, Long departmentId) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new Exception("This employee doesn't exist with id: " + employeeId));
        Department existingDepartment = departmentRepository.findById(departmentId).orElseThrow(() -> new Exception("This department doesn't exist with id: " + departmentId));

        employee.setDepartment(existingDepartment);
        return employeeRepository.save(employee);

    }
}
