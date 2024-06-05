package com.example.employee.management.sysetem.services;

import com.example.employee.management.sysetem.entitys.Attendance;
import com.example.employee.management.sysetem.entitys.Employee;
import com.example.employee.management.sysetem.projection.AttendanceProjection;
import com.example.employee.management.sysetem.repositores.AttendanceRepository;
import com.example.employee.management.sysetem.repositores.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    //    start the day to employee
    public AttendanceProjection startWorkDay(Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception("This employee doesn't exist with id: " + id));

        Attendance lastAttendanceDay = attendanceRepository.findLatestAttendanceByEmployeeId(id);

        if (lastAttendanceDay.getCheckOutTime() != null) {

            Attendance attendance = new Attendance(employee);

            attendance.setCheckInTime(LocalDateTime.now());

            attendanceRepository.save(attendance);
        }
        return attendanceRepository.findLatestAttendanceByEmployeeIdToDisplay(id);
    }

    //    end the day to employee
    public AttendanceProjection endWorkDay(Long id) throws Exception {
        Attendance lastAttendanceDay = attendanceRepository.findLatestAttendanceByEmployeeId(id);

        if (lastAttendanceDay.getCheckOutTime() == null) {
            lastAttendanceDay.setCheckOutTime(LocalDateTime.now());
            lastAttendanceDay.setCheckInTime(lastAttendanceDay.getCheckInTime());
            attendanceRepository.save(lastAttendanceDay);
        }
        return attendanceRepository.findLatestAttendanceByEmployeeIdToDisplay(id);
    }

    //    get all attendance data
    public List<AttendanceProjection> getAttendanceDataForAllEmployees() {
        return attendanceRepository.findAllAttendance();
    }

    //    get all attendance data for specific employee
    public List<AttendanceProjection> getAttendanceDataByEmployeeId(Long id) {
        return attendanceRepository.findAllAttendanceByEmployeeIdForDisplay(id);
    }

    //    get working hours in months for specific employee for the last year only
    public HashMap<Month, Long> getMonthlyWorkingHours(Long id) {
        List<AttendanceProjection> attendanceList = attendanceRepository.findAllAttendanceByEmployeeIdForDisplay(id);

        if (attendanceList.isEmpty())
            return null;

        HashMap<Month, Long> attendanceMonthDuration = new HashMap<>();
        long workingMinutesInMonth = 0;

        for (AttendanceProjection attendance :
                attendanceList) {

            if (attendance.getCheckInTime() == null || attendance.getCheckOutTime() == null)
                continue;


            workingMinutesInMonth +=
                    Duration.between(attendance.getCheckInTime(),
                            attendance.getCheckOutTime()).toMinutes();

            attendanceMonthDuration.put(
                    attendance.getCheckInTime().getMonth(), workingMinutesInMonth);

        }

        return attendanceMonthDuration;

    }
}
