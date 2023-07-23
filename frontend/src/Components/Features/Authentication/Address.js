import React, { useState } from "react";
import "./Address.css";
import RegisterBarberPopup from "./RegisterBarberPopup";

const Address = () => {
  const [state, setState] = useState("");
  const [city, setCity] = useState("");
  const [houseNo, setHouseNo] = useState("");
  const [locality, setLocality] = useState("");
  const [pincode, setPincode] = useState("");
  const [showPopup, setShowPopup] = useState(false);

  const handleRegisterCustomer = () => {
    // Logic to handle registration as a customer
    console.log("Registering as Customer");
  };

  const handleRegisterBarber = (e) => {
    e.preventDefault(); // Prevent the form from being submitted
    setShowPopup(true);
  };

  const handleClosePopup = () => {
    // Close the popup
    setShowPopup(false);
  };

  return (
    <div className="address-container">
      <h1>Address</h1>
      <form>
        <label>
          State:
          <input type="text" value={state} onChange={(e) => setState(e.target.value)} />
        </label>
        <br />
        <label>
          City:
          <input type="text" value={city} onChange={(e) => setCity(e.target.value)} />
        </label>
        <br />
        <label>
          House No.:
          <input type="text" value={houseNo} onChange={(e) => setHouseNo(e.target.value)} />
        </label>
        <br />
        <label>
          Locality:
          <input type="text" value={locality} onChange={(e) => setLocality(e.target.value)} />
        </label>
        <br />
        <label>
          Pincode:
          <input type="text" value={pincode} onChange={(e) => setPincode(e.target.value)} />
        </label>
        <br />
        <div className="button-group">
          <button onClick={handleRegisterCustomer}>Register as Customer</button>
          <button onClick={handleRegisterBarber}>Register as Barber</button>
        </div>

        {showPopup && (
          <div className="popup-container">
            <RegisterBarberPopup onClose={handleClosePopup} handleRegisterBarber={handleRegisterBarber} />
          </div>
        )}
      </form>
    </div>
  );
};

export default Address;
