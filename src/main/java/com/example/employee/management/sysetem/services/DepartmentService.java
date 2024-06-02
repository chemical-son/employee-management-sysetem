package com.example.employee.management.sysetem.services;

import com.example.employee.management.sysetem.entitys.Department;
import com.example.employee.management.sysetem.projection.DepartmentProjection;
import com.example.employee.management.sysetem.repositores.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

//    get all department
    public List<DepartmentProjection> getAllDepartments(){
        return departmentRepository.findAllDepartments();
    }

//    get department by id
    public DepartmentProjection getDepartmentById(Long id) throws Exception {
        DepartmentProjection department = departmentRepository.findDepartmentById(id);
        return department;
    }

//    adding new department
    public Department addNewDepartment(Department department){
        return departmentRepository.save(department);
    }

//    update department data
    public DepartmentProjection updateDepartmentData(Long id, Department departmentData) throws Exception {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new Exception("This department doesn't exist with id: " + id));

        department.setName(departmentData.getName() != null? departmentData.getName(): department.getName());
        department.setManager(departmentData.getManager() != null? departmentData.getManager(): department.getManager());
        department.setFunction(departmentData.getFunction() != null? departmentData.getFunction(): department.getFunction());

        departmentRepository.save(department);

        DepartmentProjection departmentProjection = departmentRepository.findDepartmentById(id);

        return departmentProjection;
    }

//    delete department by id
    public void deleteDepartment(Long id) throws Exception {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new Exception("This employee doesn't exist with id: " + id));
        departmentRepository.delete(department);
    }
}
