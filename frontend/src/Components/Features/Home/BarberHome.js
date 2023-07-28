import React from "react";
import "./BarberHome.css";
import Navbar from "../../Navbar/Navbar";
import { useNavigate } from "react-router-dom";

const BarberHome = () => {
  const navigate = useNavigate();

  const handleAddShift = () => {
    navigate("/barbershift");
  };

  const handleAddInventory = () => {
    // Navigate to BarberInventory.js
    navigate("/barberinventory");
  };

  const handleAddHoliday = () => {
    navigate("/barberholiday");
  };

  return (
    <div>
      <Navbar />
      <div className="barber-home-container">
        <h1>Welcome to Barber Home</h1>
        <div className="button-container">
          <button onClick={handleAddShift}>Add Shift</button>
          <button onClick={handleAddInventory}>Add Inventory</button>
          <button onClick={handleAddHoliday}>Add Holiday</button>
        </div>
        {/* Other content of the Barber Home page goes here */}
      </div>
    </div>
  );
};

export default BarberHome;

