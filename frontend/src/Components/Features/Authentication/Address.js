import React, { useState } from "react";
import "./Address.css";
import RegisterBarberPopup from "./RegisterBarberPopup";
import { postData } from "../../postApi";
import { useNavigate } from "react-router-dom/dist";
import { useEffect } from "react";
function Address() {
useEffect(() => {
 
const authToken = {
      userEmail: localStorage.getItem("user_emailId"),
    };
    postData(authToken, "/user-authentication/checkToken/")
      .then((response) => {
        if (response.status === 200) {
          if(response.data != 'true'){
            navigate("/")
          }
        } else {
        }
      })
      .catch((error) => {
        console.error("Error booking slot:", error);
      });

  }, []);
  const [state, setState] = useState("");
  const [city, setCity] = useState("");
  const [houseNo, setHouseNo] = useState("");
  const [locality, setLocality] = useState("");
  const [pincode, setPincode] = useState("");
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();

  const handleRegisterCustomer = (e) => {
    e.preventDefault();
    const address = {
      userId: localStorage.getItem("user_emailId"),
      state: state,
      city: city,
      house: houseNo,
      locality: locality,
      pinCode: pincode,
      createdBy:localStorage.getItem("user_emailId")
    };



    postData(JSON.stringify(address), "/address/")
    .then((response) => {
      if (response.status === 200) {
        navigate("/");
        console.log("addrss added:", response.data);
      }
    })
    .catch((error) => {
      console.error("Error creating user:", error);
    });

  };

  const handleRegisterBarber = (e) => {
    e.preventDefault();
    const address = {
      userId: localStorage.getItem("user_emailId"),
      state: state,
      city: city,
      house: houseNo,
      locality: locality,
      pinCode: pincode,
      createdBy: localStorage.getItem("user_emailId")
    };



    postData(JSON.stringify(address), "/address/")
    .then((response) => {
      if (response.status === 200) {
        // navigate("/");
        setShowPopup(true);
        console.log("addrss added:", response.data);
      }
    })
    .catch((error) => {
      console.error("Error creating user:", error);
    });
    // handleRegisterCustomer();
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
            <RegisterBarberPopup onClose={handleClosePopup} handleRegisterBarber={handleRegisterBarber} class="popupalignment" />
          </div>
        )}
      </form>
    </div>
  );
};

export default Address;
