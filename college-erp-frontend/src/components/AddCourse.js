// AddCourse Component
import React, { useState } from "react";
import axios from "axios";

const AddCourse = () => {
  const [formData, setFormData] = useState({
    courseId: "",
    courseName: "",
    deptId: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setFormData({
      courseId: "",
      courseName: "",
      deptId: "",
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const user = JSON.parse(localStorage.getItem('user'));
      const token = user?.jwt; // Optional chaining to avoid errors
      const pre="Bearer";
      if (!token) throw new Error("Token not found in localStorage.");

      await axios.post("http://localhost:6060/admin/addCourse", formData, {
        headers: { Authorization: `${pre} ${token}` },
      });

      alert("Course added successfully!");
      resetForm();
    } catch (error) {
      alert(error.response ? `Failed to add course: ${error.response.data}` : error.message);
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-4">Add Course</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="courseId"
          placeholder="Course ID"
          value={formData.courseId}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <input
          type="text"
          name="courseName"
          placeholder="Course Name"
          value={formData.courseName}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <input
          type="text"
          name="deptId"
          placeholder="Department ID"
          value={formData.deptId}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <button
          type="submit"
          className={`${
            formData.courseId && formData.courseName && formData.deptId
              ? "bg-blue-500 hover:bg-blue-600"
              : "bg-gray-300 cursor-not-allowed"
          } text-white px-4 py-2 rounded`}
          disabled={!formData.courseId || !formData.courseName || !formData.deptId}
        >
          Add Course
        </button>
      </form>
    </div>
  );
};

export default AddCourse;
