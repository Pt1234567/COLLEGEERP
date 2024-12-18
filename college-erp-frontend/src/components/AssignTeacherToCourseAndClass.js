import React, { useState } from "react";
import axios from "axios";

const AssignTeacherToCourseAndClass = () => {
  const [formData, setFormData] = useState({
    teacherId: "",
    courseId: "",
    classId: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setFormData({
      teacherId: "",
      courseId: "",
      classId: "",
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const user = JSON.parse(localStorage.getItem('user'));
      const token = user?.jwt; // Optional chaining to avoid errors
      const pre="Bearer";
      if (!token) throw new Error("Token not found in localStorage.");

      await axios.post("http://localhost:6060/admin/assign", formData, {
        headers: { Authorization: `${pre} ${token}`},
      });

      alert("Teacher assigned successfully!");
      resetForm();
    } catch (error) {
      alert(error.response ? `Failed to assign teacher: ${error.response.data}` : error.message);
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-4">Assign Teacher to Course and Class</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="teacherId"
          placeholder="Teacher ID"
          value={formData.teacherId}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
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
          name="classId"
          placeholder="Class ID"
          value={formData.classId}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <button
          type="submit"
          className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded"
        >
          Assign
        </button>
      </form>
    </div>
  );
};

export default AssignTeacherToCourseAndClass;
