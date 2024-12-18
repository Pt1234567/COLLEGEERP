import React, { useState } from "react";
import axios from "axios";

const AddDepartment = () => {
  const [formData, setFormData] = useState({
    id: "",
    deptName: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setFormData({
      id: "",
      deptName: "",
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const user = JSON.parse(localStorage.getItem('user'));
      const token = user?.jwt; // Optional chaining to avoid errors
      const pre="Bearer";
      if (!token) throw new Error("Token not found in localStorage.");

      await axios.post("http://localhost:6060/admin/addDepartment", formData, {
        headers: { Authorization: `${pre} ${token}`},
      });

      alert("Department added successfully!");
      resetForm();
    } catch (error) {
      alert(error.response ? `Failed to add department: ${error.response.data}` : error.message);
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-4">Add Department</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="id"
          placeholder="Department ID"
          value={formData.id}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <input
          type="text"
          name="deptName"
          placeholder="Department Name"
          value={formData.deptName}
          onChange={handleChange}
          className="w-full px-4 py-2 border rounded"
          required
        />
        <button
          type="submit"
          className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded"
        >
          Add Department
        </button>
      </form>
    </div>
  );
};

export default AddDepartment;
