import React from "react";
import { useNavigate } from "react-router-dom";

const Navbar = ({ user, setUser }) => {
  const navigate = useNavigate();

  const handleLogin = () => {
    navigate("/login");
  };

  const handleLogout = () => {
    setUser(null); // Clear user state
    localStorage.removeItem("user"); // Remove user from localStorage
    navigate("/"); // Redirect to homepage or login page
  };

  return (
    <nav className="bg-blue-500 px-4 py-2 flex justify-between items-center shadow-md">
      {/* Application Name (on the left side) */}
      <div
        className="text-white font-bold text-xl cursor-pointer"
        onClick={() => navigate("/")}
      >
        College ERP
      </div>

      {/* Conditional Rendering based on user login status */}
      {user ? (
        <div className="flex items-center space-x-4">
          <span className="text-white font-semibold">
            {user.userName}
          </span> {/* Display username and role */}
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
