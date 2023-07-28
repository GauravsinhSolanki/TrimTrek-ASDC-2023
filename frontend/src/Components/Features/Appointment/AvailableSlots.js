import React from "react";
import { postData } from "../../postApi";

const AvailableSlots = ({ slots }) => {
  const  handleSlotClick = (slot) => {
    const customerId = localStorage.getItem("user_emailId"); 
    if (!customerId) {
      alert("Customer ID not found. Please log in or sign up.");
      return;
    }

    const slotBookingDto = {
      serviceId: slot.serviceId,
      serviceName: slot.serviceName,
      serviceDate: slot.serviceDate,
      serviceDuration: slot.serviceDuration,
      startTime: slot.startTime,
      endTime: slot.endTime,
      customerId: customerId,
      barberId: slot.barberId,
    };

    postData(slotBookingDto, "/bookDto-slot")
      .then((response) => {
        if (response.status === 200) {
          alert("Your booking is done!");
        } else {
          alert("Booking failed. Please try again.");
        }
      })
      .catch((error) => {
        console.error("Error booking slot:", error);
        alert("Booking failed. Please try again.");
      });
  };

  return (
    <div className="available-slots-container">
      <h2>Available Slots</h2>
      <div className="slots-wrapper">
        {slots.map((slot) => (
          <div
            key={slot.id}
            className="slot-item"
            style={{
              border: "1px solid #ccc",
              padding: "10px",
              marginBottom: "10px",
              borderRadius: "5px",
              background: "#f7f7f7",
              cursor: "pointer",
            }}
            onClick={() => handleSlotClick(slot)}
          >
            <div
              className="barber-id"
              style={{
                fontWeight: "bold",
                fontSize: "18px",
              }}
            >
              Barber ID: {slot.barberId}
            </div>
            <div className="time-slot" style={{ marginTop: "5px" }}>
              <span style={{ fontWeight: "bold" }}>Time Slot:</span>{" "}
              <span>{slot.startTime}</span> - <span>{slot.endTime}</span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AvailableSlots;