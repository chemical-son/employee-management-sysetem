package com.example.employee.management.sysetem.repositores;

import com.example.employee.management.sysetem.entitys.Department;
import com.example.employee.management.sysetem.projection.DepartmentProjection;
import com.example.employee.management.sysetem.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d JOIN d.manager m")
    List<DepartmentProjection> findAllDepartments();

    @Query("SELECT d FROM Department d JOIN d.manager m WHERE d.id = :id")
    DepartmentProjection findDepartmentById(@Param("id") Long id);

}
