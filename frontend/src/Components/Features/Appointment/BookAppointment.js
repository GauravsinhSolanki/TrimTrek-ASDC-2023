import React, { useState, useEffect } from "react";
import Navbar from "../../Navbar/Navbar";
import { getData } from "../../getApi";
import AvailableSlots from "./AvailableSlots";
import { postData } from "../../postApi";
import { useNavigate} from "react-router-dom";

import "./BookAppointment.css";
function BookAppointment() {
  const navigate = useNavigate();

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
  const [selectedDate, setSelectedDate] = useState("");
  const [selectedService, setSelectedService] = useState("");
  const [serviceIdsArray, setServiceIdsArray] = useState([]);
  const [responseData, setResponseData] = useState([]);
  const [availableSlots, setAvailableSlots] = useState([]);


  useEffect(() => {
    const url = "/barberservice/all-barber-services";
    getData(url, {})
      .then((response) => {
        if (response.status === 200) {
          const responseData = response.data;
          setResponseData(responseData);
          const serviceIds = responseData.map((service) => service.serviceId);
          setServiceIdsArray(serviceIds);
          
        } else {
          alert("Invalid Credential");
        }
      })
      .catch((error) => {
        console.error("Error logging in:", error);
      });
  }, []);

  const handleFormSubmit = (e) => {
    e.preventDefault();
    const customerId = localStorage.getItem("user_emailId"); // Fetch the customerId from the localStorage
    const selectedServiceData = responseData.find((service) => service.serviceId === selectedService);

    if (customerId && selectedServiceData && selectedDate) {
      const { serviceDuration } = selectedServiceData; // Get the serviceDuration from the selectedServiceData

      const url = `/slots-for-given-request/${serviceDuration}/${selectedDate}/${customerId}`;
      
      getData(url, {})
        .then((response) => {
          if (response.status === 200) {
            const slotsData = response.data;
            setAvailableSlots(slotsData);
            console.log("Appointment booked successfully!");
          } else {
            console.log("Failed to book appointment.");
          }
        })
        .catch((error) => {
          console.error("Error booking appointment:", error);
        });
    } else {
      console.log("Missing required data for booking appointment.");
    }
  };

  return (
    <div>
      <Navbar />
      <div className="book-appointment-container">
        <h1>Book an Appointment</h1>
        <form onSubmit={handleFormSubmit}>
          <div className="appointment-form-group">
            <label htmlFor="selectedDate">Select Date:</label>
            <input
              type="date"
              id="selectedDate"
              value={selectedDate}
              onChange={(e) => setSelectedDate(e.target.value)}
              required
            />
          </div>
          <div className="appointment-form-group">
            <label htmlFor="selectedService">Select Service:</label>
            <select
              id="selectedService"
              value={selectedService}
              onChange={(e) => setSelectedService(e.target.value)}
              required
            >
              <option value="">Select a service</option>
              {serviceIdsArray.map((serviceId) => (
                <option key={serviceId} value={serviceId}>
                  {serviceId}
                </option>
              ))}
            </select>
          </div>
          <div className="book-appointment">
            <button type="submit">Generate Available Slots</button>
          </div>
        </form>
      </div>
      {availableSlots.length > 0 && <AvailableSlots slots={availableSlots} />}
    </div>
  );
};

export default BookAppointment;