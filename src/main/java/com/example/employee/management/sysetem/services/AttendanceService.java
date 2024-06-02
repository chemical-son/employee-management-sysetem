package com.example.employee.management.sysetem.services;

import com.example.employee.management.sysetem.entitys.Attendance;
import com.example.employee.management.sysetem.entitys.Employee;
import com.example.employee.management.sysetem.projection.AttendanceProjection;
import com.example.employee.management.sysetem.repositores.AttendanceRepository;
import com.example.employee.management.sysetem.repositores.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

//    start the day to employee
    public AttendanceProjection startWorkDay(Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new Exception("This employee doesn't exist with id: " + id));

        Attendance attendance = new Attendance(employee);

        attendance.setCheckInTime(LocalDateTime.now());

        attendanceRepository.save(attendance);
        return attendanceRepository.findLatestAttendanceByEmployeeIdToDisplay(id);
    }

//    end the day to employee
    public AttendanceProjection endWorkDay(Long id) throws Exception {
        Attendance lastAttendanceDay = attendanceRepository.findLatestAttendanceByEmployeeId(id);

        lastAttendanceDay.setCheckOutTime(LocalDateTime.now());
        lastAttendanceDay.setCheckInTime(lastAttendanceDay.getCheckInTime());
        attendanceRepository.save(lastAttendanceDay);
        return attendanceRepository.findLatestAttendanceByEmployeeIdToDisplay(id);
    }
}
