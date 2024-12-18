import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const LoginPage = ({ setUser }) => {
  const [formData, setFormData] = useState({ username: "", password: "" });
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setError(""); // Clear error on change
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { username, password } = formData;

    if (!username || !password) {
      setError("Both fields are required");
      return;
    }

    try {
      setIsLoading(true);
      const response = await axios.post("http://localhost:6060/auth/signIn", {
        username,
        password,
      });

      const user = response.data; // Assume this contains username, role, and token
      setUser(user); // Save user data to state
      

      // Redirect based on role
      if (user.role === "ROLE_ADMIN") {
        navigate("/AdminDashboard");
      } else if (user.role === "ROLE_STUDENT") {
        navigate("/student-dashboard");
      } else if (user.role === "ROLE_TEACHER") {
        navigate("/TeacherDashboard");
      } else {
        setError("Role not recognized");
      }
    } catch (err) {
      console.error("Login failed", err.response?.data || err.message);
      setError("Invalid credentials, please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <form
        className="bg-white p-8 rounded shadow-md w-96"
        onSubmit={handleSubmit}
      >
        <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>
        {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
        <div className="mb-4">
          <label className="block text-gray-700">Username</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            placeholder="Enter your username"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Password</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            placeholder="Enter your password"
          />
        </div>
        <button
          type="submit"
          disabled={isLoading}
          className={`w-full py-2 text-white font-bold rounded-md ${
            isLoading
              ? "bg-gray-400 cursor-not-allowed"
              : "bg-blue-500 hover:bg-blue-600"
          }`}
        >
          {isLoading ? "Logging in..." : "Login"}
        </button>
      </form>
    </div>
  );
};

export default LoginPage;
