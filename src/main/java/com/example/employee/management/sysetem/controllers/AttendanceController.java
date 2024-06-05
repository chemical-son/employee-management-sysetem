package com.example.employee.management.sysetem.controllers;

import com.example.employee.management.sysetem.projection.AttendanceProjection;
import com.example.employee.management.sysetem.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.HashMap;
import java.util.List;

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
        return attendanceService.endWorkDay(employeeId);
    }

//    get all attendance data
    @GetMapping
    public List<AttendanceProjection> getAttendanceDataForAllEmployee(){
        return attendanceService.getAttendanceDataForAllEmployees();
    }

//    get all attendance for a specific employee
    @GetMapping("/{employee_id}")
    public List<AttendanceProjection> getAttendanceDataForEmployeeById(@PathVariable(name = "employee_id") Long id){
        return attendanceService.getAttendanceDataByEmployeeId(id);
    }

//    get working hours in last months for specific employee
    @GetMapping("/working_hours/{employee_id}")
    public HashMap<Month, Long> getWorkingHours(@PathVariable(name = "employee_id") Long id){
        return attendanceService.getMonthlyWorkingHours(id);
    }
}
