package com.example.employee.management.sysetem.repositores;

import com.example.employee.management.sysetem.entitys.Attendance;
import com.example.employee.management.sysetem.projection.AttendanceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId ORDER BY a.checkInTime DESC LIMIT 1")
    Attendance findLatestAttendanceByEmployeeId(@Param("employeeId") Long employeeId);


    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId ORDER BY a.checkInTime DESC LIMIT 1")
    AttendanceProjection findLatestAttendanceByEmployeeIdToDisplay(@Param("employeeId") Long employeeId);

}
