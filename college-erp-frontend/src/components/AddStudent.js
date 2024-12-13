import React, { useState } from "react";
import axios from "axios";

const AddStudent = () => {
  const [formData, setFormData] = useState({
    classId: "",
    studentName: "",
    studentId: "",
    dob: "",
    gender: "MALE",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setFormData({
      classId: "",
      studentName: "",
      studentId: "",
      dob: "",
      gender: "MALE",
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem('jwt');
      if (!token) {
        throw new Error("Token not found in localStorage.");
      }

      console.log(token); // Log the token
      console.log(`Bearer ${token}`);
      
      const response = await axios.post("http://localhost:6060/admin/addStudent", formData, {
        headers: {
          Authorization: "Bearer"+token // Properly use the token with "Bearer"
        },
      });

      alert("Student added successfully!");
      resetForm();
    } catch (error) {
      if (error.response) {
        alert(`Failed to add student: ${error.response.data}`);
      } else if (error.request) {
        alert("No response from the server. Please try again later.");
      } else {
        alert(`Error: ${error.message}`);
      }
      console.error("Error adding student:", error);
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-4">Add Student</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="studentName"
          placeholder="Student Name"
          value={formData.studentName}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <input
          type="text"
          name="studentId"
          placeholder="Student ID"
          value={formData.studentId}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <input
          type="date"
          name="dob"
          value={formData.dob}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <select
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
        >
          <option value="MALE">Male</option>
          <option value="FEMALE">Female</option>
        </select>
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
          className={`${
            formData.studentName && formData.studentId && formData.dob && formData.classId
              ? "bg-blue-500 hover:bg-blue-600"
              : "bg-gray-300 cursor-not-allowed"
          } text-white px-4 py-2 rounded`}
          disabled={!formData.studentName || !formData.studentId || !formData.dob || !formData.classId}
        >
          Add Student
        </button>
      </form>
    </div>
  );
};

export default AddStudent;
