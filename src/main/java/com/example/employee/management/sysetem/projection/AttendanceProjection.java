package com.example.employee.management.sysetem.projection;

import java.time.LocalDateTime;

public interface AttendanceProjection {
    Long getId();
    EmployeeInfo getEmployee();
    LocalDateTime getCheckInTime();
    LocalDateTime getCheckOutTime();

    interface EmployeeInfo{
        Long getId();
        String getFullName();
    }
}
