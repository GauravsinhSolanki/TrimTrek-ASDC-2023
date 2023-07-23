import React, { useState } from "react";

const RegisterBarberPopup = ({ onClose, handleRegisterBarber }) => {
  const [services, setServices] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your logic to handle the form submission (e.g., registration)
    console.log("Registering as Barber with services:", services);
    onClose(); // Close the popup after registration (you can modify this as needed)
  };

  return (
    <div className="popup">
      <h2>Register as Barber</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Services:
          <input type="text" value={services} onChange={(e) => setServices(e.target.value)} />
        </label>
        <br />
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterBarberPopup;