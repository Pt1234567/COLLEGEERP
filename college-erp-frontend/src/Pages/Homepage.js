import React from "react";

const Homepage = ({user,setUser}) => {
  return (
    <div>
    

      {/* Main Content */}
      <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
        <h1 className="text-3xl font-bold mb-4">Welcome to College ERP</h1>
        <p className="text-gray-700 text-lg">
          Manage your college activities efficiently and effortlessly.
        </p>
      </div>
    </div>
  );
};

export default Homepage;
