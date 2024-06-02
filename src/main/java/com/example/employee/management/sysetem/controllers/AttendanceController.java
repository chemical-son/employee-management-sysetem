package com.example.employee.management.sysetem.controllers;

import com.example.employee.management.sysetem.entitys.Attendance;
import com.example.employee.management.sysetem.projection.AttendanceProjection;
import com.example.employee.management.sysetem.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

//    start work day to an employee
    @PostMapping("/start/{employeeId}")
    public AttendanceProjection startWorkDay(@PathVariable Long employeeId) throws Exception {
        return attendanceService.startWorkDay(employeeId);
    }

    //    end work day to an employee
    @PutMapping("/end/{employeeId}")
    public AttendanceProjection endWorkDay(@PathVariable Long employeeId) throws Exception {
        attendanceService.endWorkDay(employeeId);
        return attendanceService.endWorkDay(employeeId);
    }

}
