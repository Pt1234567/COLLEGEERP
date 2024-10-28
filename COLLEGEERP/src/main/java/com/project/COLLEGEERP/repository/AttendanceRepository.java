    package com.project.COLLEGEERP.repository;

    import com.project.COLLEGEERP.entities.Attendance;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
        Attendance findByStudent_StudentIdAndCourse_CourseId(String StudentId,String courseId);
    }
