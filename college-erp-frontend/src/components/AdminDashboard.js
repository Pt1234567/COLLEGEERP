import React from "react";
import { useNavigate } from "react-router-dom";

const AdminDashboard = () => {
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center p-8">
      <h1 className="text-3xl font-bold mb-6">Admin Dashboard</h1>
      <div className="grid grid-cols-2 gap-6">
        <button
          onClick={() => handleNavigation("/admin/add-student")}
          className="bg-blue-500 text-white py-3 px-6 rounded shadow hover:bg-blue-600"
        >
          Add Student
        </button>
        <button
          onClick={() => handleNavigation("/admin/add-teacher")}
          className="bg-green-500 text-white py-3 px-6 rounded shadow hover:bg-green-600"
        >
          Add Teacher
        </button>
        <button
          onClick={() => handleNavigation("/admin/add-class")}
          className="bg-yellow-500 text-white py-3 px-6 rounded shadow hover:bg-yellow-600"
        >
          Add Class
        </button>
        <button
          onClick={() => handleNavigation("/admin/add-course")}
          className="bg-purple-500 text-white py-3 px-6 rounded shadow hover:bg-purple-600"
        >
          Add Course
        </button>
        <button
          onClick={() => handleNavigation("/admin/add-department")}
          className="bg-indigo-500 text-white py-3 px-6 rounded shadow hover:bg-indigo-600"
        >
          Add Department
        </button>
        <button
          onClick={() => handleNavigation("/admin/assign-course")}
          className="bg-red-500 text-white py-3 px-6 rounded shadow hover:bg-red-600"
        >
          Assign Teacher to Course
        </button>
      </div>
    </div>
  );
};

export default AdminDashboard;
