package com.example.employee.management.sysetem.repositores;

import com.example.employee.management.sysetem.entitys.Employee;
import com.example.employee.management.sysetem.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e JOIN e.department d JOIN d.manager m")
    List<EmployeeProjection> findAllEmployeesWithDepartmentAndManager();

    @Query("SELECT e FROM Employee e JOIN e.department d JOIN d.manager m WHERE e.id = :id")
    EmployeeProjection findEmployeeById(@Param("id") Long id);

}
