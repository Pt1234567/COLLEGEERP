import React, { useState } from "react";
import axios from "axios";

const AddTeacher = () => {
  const [formData, setFormData] = useState({
    teacherId: "",
    name: "",
    dob: "",
    gender: "MALE",
    email: "",
    deptId: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setFormData({
      teacherId: "",
      name: "",
      dob: "",
      gender: "MALE",
      email: "",
      deptId: "",
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem('jwt');
      if (!token) throw new Error("Token not found in localStorage.");

      await axios.post("http://localhost:6060/admin/addTeacher", formData, {
        headers: { Authorization: "Bearer"+token },
      });

      alert("Teacher added successfully!");
      resetForm();
    } catch (error) {
      alert(error.response ? `Failed to add teacher: ${error.response.data}` : error.message);
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-4">Add Teacher</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="name"
          placeholder="Teacher Name"
          value={formData.name}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
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
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
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
            formData.name && formData.teacherId && formData.dob && formData.email && formData.deptId
              ? "bg-blue-500 hover:bg-blue-600"
              : "bg-gray-300 cursor-not-allowed"
          } text-white px-4 py-2 rounded`}
          disabled={!formData.name || !formData.teacherId || !formData.dob || !formData.email || !formData.deptId}
        >
          Add Teacher
        </button>
      </form>
    </div>
  );
};

export default AddTeacher;
