import React, { useState, useEffect } from "react";
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar'; // Assuming you have a Navbar component
import Homepage from './Pages/Homepage';
import LoginPage from "./components/LoginPage";
import AdminDashboard from "./components/AdminDashboard";
import AddClass from "./components/AddClass";
import AddCourse from "./components/AddCourse";
import AddDepartment from "./components/AddDepartment";
import AddStudent from './components/AddStudent';
import AddTeacher from "./components/AddTeacher";
import AssignCourse from "./components/AssignTeacherToCourseAndClass";
import TeacherDashboard from "./components/TeacherDashboard";


function App() {
  const [user, setUser] = useState(() => {
    const savedUser = localStorage.getItem('user');
    return savedUser ? JSON.parse(savedUser) : null; // Initialize from localStorage if available
  });

  useEffect(() => {
    if (user) {
      localStorage.setItem('user', JSON.stringify(user)); // Save user to localStorage on login
    }
  }, [user]);

  return (
    <Router>
      <Navbar user={user} setUser={setUser} />
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="/login" element={<LoginPage setUser={setUser} />} />
        <Route path="/AdminDashboard" element={<AdminDashboard />} />
        <Route path="/TeacherDashboard" element={<TeacherDashboard />} />
        <Route path="/AdminDashboard" element={<AdminDashboard />} />
        <Route path="/admin/add-student" element={<AddStudent />} />
        <Route path="/admin/add-teacher" element={<AddTeacher />} />
        <Route path="/admin/add-class" element={<AddClass />} />
        <Route path="/admin/add-course" element={<AddCourse />} />
        <Route path="/admin/add-department" element={<AddDepartment />} />
        <Route path="/admin/assign-course" element={<AssignCourse />} />
      </Routes>
    </Router>
  );
}

export default App;
