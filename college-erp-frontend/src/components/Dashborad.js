import React from "react";
import Navbar from "./Navbar"; // Assuming you have a Navbar component

const Dashboard = ({ user }) => {
  // Check if user is null before accessing user properties
  if (!user) {
    return (
      <div className="min-h-screen bg-gray-100">
        <div className="flex justify-center items-center h-screen">
          <div className="bg-white p-8 rounded shadow-md w-96 text-center">
            <h2 className="text-2xl font-bold mb-4">Please log in to access the Dashboard.</h2>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100">
      <div className="flex justify-center items-center h-screen">
        <div className="bg-white p-8 rounded shadow-md w-96 text-center">
          <h2 className="text-2xl font-bold mb-4">Welcome to the Dashboard!</h2>
          <p className="text-gray-600">You are logged in as {user.username}</p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
