import React, { useState } from "react";
import { useNavigate } from "react-router-dom/dist";
import {putData} from "../../putApi";


const RegisterBarberPopup = ({ onClose, handleRegisterBarber }) => {

  const [services, setServices] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("inside handle submit")
    const servicesData={
      emailId:localStorage.getItem("user_emailId"),
      speciality:services
    };
    console.log(servicesData.speciality);
    putData(JSON.stringify(servicesData), "/user/barber-profile-create")
    .then((response) => {

      if (response.status === 200) {
        navigate("/");
        console.log("addrss added:", response.data);
      }
    })
    .catch((error) => {
      console.error("Error creating user:", error);
    });

    console.log("Registering as Barber with services:", services);
    onClose(); // Close the popup after registration (you can modify this as needed)
  };

  return (
    <div className="popup">
      <h2>Register as Barber</h2>
      <form >
        <label>
          Speciality:
          <input type="text" value={services} onChange={(e) => setServices(e.target.value)} />
        </label>
        <br />
        <button type="submit" onClick={handleSubmit}>Register</button>
      </form>
    </div>
  );
};

export default RegisterBarberPopup;