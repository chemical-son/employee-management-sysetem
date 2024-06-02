package com.example.employee.management.sysetem.projection;

public interface EmployeeProjection {
    Long getId();
    String getFullName();
    String getDateOfBirth();
    String getAddress();
    Long getSalary();
    DepartmentInfo getDepartment();

    interface DepartmentInfo {
        String getName();
        ManagerInfo getManager();
    }

    interface ManagerInfo {
        String getFullName();
    }
}
