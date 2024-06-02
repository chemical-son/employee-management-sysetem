package com.example.employee.management.sysetem.projection;

public interface DepartmentProjection {
    Long getId();
    String getName();
    ManagerInfo getManager();
    String getFunction();

    interface ManagerInfo {
        Long getId();
        String getFullName();
    }
}
