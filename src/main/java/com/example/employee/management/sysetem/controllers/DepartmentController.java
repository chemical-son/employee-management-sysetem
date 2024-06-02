package com.example.employee.management.sysetem.controllers;


import com.example.employee.management.sysetem.entitys.Department;
import com.example.employee.management.sysetem.projection.DepartmentProjection;
import com.example.employee.management.sysetem.services.DepartmentService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //    get department by id
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentProjection> getDepartmentById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(departmentService.getDepartmentById(id));
    }


//    get all departments
    @GetMapping
    public List<DepartmentProjection> getAllDepartments(){
        return departmentService.getAllDepartments();
    }


//    adding new Department
    @PostMapping
    public Department addingNewDepartment(@RequestBody Department department){
        return departmentService.addNewDepartment(department);
    }

//    update Department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentProjection> updateDepartment(@PathVariable Long id, @RequestBody Department departmentData) throws Exception {
        DepartmentProjection updatedDepartment = departmentService.updateDepartmentData(id, departmentData);
        return ResponseEntity.ok(updatedDepartment);
    }

//    delete Department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) throws Exception {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}
