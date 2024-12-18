import React, { useState, useEffect } from "react";
import axios from "axios";

const TeacherDashboard = () => {
  const [classes, setClasses] = useState([]);
  const [courses, setCourses] = useState([]);
  const [selectedClass, setSelectedClass] = useState("");
  const [selectedCourse, setSelectedCourse] = useState("");
  const [students, setStudents] = useState([]);
  const [attendance, setAttendance] = useState({});
  const [message, setMessage] = useState("");

  const user = JSON.parse(localStorage.getItem('user'));
  const token = user?.jwt; // Optional chaining to avoid errors
  const pre = "Bearer";

  useEffect(() => {
    fetchClassesAndCourses();
  }, []);

  const fetchClassesAndCourses = async () => {
    try {
      const response = await axios.get(
        "http://localhost:6060/teacher/classes-and-courses",
        { headers: { Authorization: `${pre} ${token}` } }
      );
      setClasses(response.data.classes || []);
      setCourses(response.data.courses || []);
    } catch (error) {
      console.error("Error fetching classes and courses", error);
      alert("Failed to fetch classes and courses");
    }
  };

  const fetchStudents = async () => {
    if (!selectedClass || !selectedCourse) {
      alert("Please select a class and course.");
      return;
    }
    try {
      const response = await axios.get(
        `http://localhost:6060/teacher/classes/${selectedClass}/courses/${selectedCourse}/attendance`,
        { headers: { Authorization: `${pre} ${token}` } }
      );
      setStudents(response.data || []);
      setAttendance(
        response.data.reduce((acc, student) => {
          acc[student.studentId] = "ABSENT"; // Default to ABSENT
          return acc;
        }, {})
      );
    } catch (error) {
      console.error("Error fetching students", error);
      alert("Failed to fetch students.");
    }
  };

  const handleAttendanceChange = (studentId, status) => {
    setAttendance({ ...attendance, [studentId]: status });
  };

  const markAttendance = async () => {
    try {
      for (const studentId in attendance) {
        const status = attendance[studentId];
        await axios.post(
          `http://localhost:6060/teacher/classes/${selectedClass}/courses/${selectedCourse}/attendance`,
          { studentId, status },
          { headers: { Authorization: `${pre} ${token}` } }
        );
      }
      setMessage("Attendance marked successfully!");
    } catch (error) {
      console.error("Error marking attendance", error);
      alert("Failed to mark attendance.");
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-6">Teacher Dashboard</h1>

      {/* Class and Course Selection */}
      <div className="mb-6">
        <label className="block font-medium mb-2">Select Class</label>
        <select
          value={selectedClass}
          onChange={(e) => setSelectedClass(e.target.value)}
          className="w-full px-4 py-2 border rounded mb-4"
        >
          <option value="">-- Select Class --</option>
          {classes.map((cls) => (
            <option key={cls} value={cls}>
              {cls}
            </option>
          ))}
        </select>

        <label className="block font-medium mb-2">Select Course</label>
        <select
          value={selectedCourse}
          onChange={(e) => setSelectedCourse(e.target.value)}
          className="w-full px-4 py-2 border rounded"
        >
          <option value="">-- Select Course --</option>
          {courses.map((course) => (
            <option key={course} value={course}>
              {course}
            </option>
          ))}
        </select>

        <button
          onClick={fetchStudents}
          className="bg-blue-500 text-white px-4 py-2 mt-4 rounded hover:bg-blue-600"
        >
          View Students
        </button>
      </div>

      {/* Student List */}
      {students.length > 0 && (
        <div>
          <h2 className="text-xl font-semibold mb-4">
            Students in Class {selectedClass} - Course {selectedCourse}
          </h2>
          <table className="w-full table-auto border-collapse border border-gray-300 mb-6">
            <thead>
              <tr>
                <th className="border px-4 py-2">Student ID</th>
                <th className="border px-4 py-2">Student Name</th>
                <th className="border px-4 py-2">Attendance</th>
              </tr>
            </thead>
            <tbody>
              {students.map((student) => (
                <tr key={student.studentId}>
                  <td className="border px-4 py-2">{student.studentId}</td>
                  <td className="border px-4 py-2">{student.studentName}</td>
                  <td className="border px-4 py-2">
                    <select
                      value={attendance[student.studentId]}
                      onChange={(e) =>
                        handleAttendanceChange(student.studentId, e.target.value)
                      }
                      className="px-2 py-1 border rounded"
                    >
                      <option value="PRESENT">Present</option>
                      <option value="ABSENT">Absent</option>
                    </select>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <button
            onClick={markAttendance}
            className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
          >
            Submit Attendance
          </button>
        </div>
      )}

      {/* Success Message */}
      {message && (
        <div className="mt-4 text-green-600 font-medium">{message}</div>
      )}
    </div>
  );
};

export default TeacherDashboard;
