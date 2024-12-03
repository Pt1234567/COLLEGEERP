import React from "react";
import { useNavigate } from "react-router-dom";

const Navbar = ({ user, setUser }) => {
  const navigate = useNavigate();

  const handleLogin = () => {
    navigate("/login"); // Redirect to login page
  };

  const handleLogout = () => {
    setUser(null); // Set user state to null (logged out)
    navigate("/"); // Redirect to homepage or login page
    localStorage.removeItem('user'); // Optionally, clear any stored user data from localStorage
  };

  return (
    <nav className="bg-blue-500 px-4 py-2 flex justify-between items-center shadow-md">
      {/* Application Name (on the left side) */}
      <div className="text-white font-bold text-xl">College ERP</div>

      {/* Conditional Rendering based on user login status */}
      {user ? (
        <div className="flex items-center space-x-4">
          <span className="text-white font-semibold">{user.username}</span> {/* Display username */}
          <button
            onClick={handleLogout}
            className="bg-white text-blue-500 font-medium px-4 py-2 rounded hover:bg-gray-200"
          >
            Logout
          </button>
        </div>
      ) : (
        <button
          onClick={handleLogin}
          className="bg-white text-blue-500 font-medium px-4 py-2 rounded hover:bg-gray-200"
        >
          Login
        </button>
      )}
    </nav>
  );
};

export default Navbar;
