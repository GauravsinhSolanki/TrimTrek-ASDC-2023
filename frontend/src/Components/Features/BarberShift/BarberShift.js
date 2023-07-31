import React, { useState } from 'react';
import Navbar from '../../Navbar/Navbar';
import './BarberShift.css';
import { postData } from "../../postApi";
import { useEffect } from "react";

function BarberShift() {
useEffect(() => {
 
const authToken = {
      userEmail: localStorage.getItem("user_emailId"),
    };
    postData(authToken, "/user-authentication/checkToken/")
      .then((response) => {
        if (response.status === 200) {
          if(response.data !== 'true'){
            window.location.href="/"
          }
        } else {
        }
      })
      .catch((error) => {
        console.error("Error booking slot:", error);
      });

  }, []);
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const [dayOfWeek, setDayOfWeek] = useState('');

  const handleSubmit = () => {
    console.log('Start Time:', startTime);
    console.log('End Time:', endTime);
    console.log('Day of Week:', dayOfWeek);

    const email = localStorage.getItem("user_emailId");

    const BarberShiftData={
      barberId : email ,
      startTime: startTime,
      endTime: endTime,
      dayOfWeek: dayOfWeek,
      createdBy: email
    }

    postData(JSON.stringify(BarberShiftData), "/barber/add-shift")
      .then((response) => {
        if (response.status === 200) {
          alert("Barber Shift added");
          localStorage.setItem("user_emailId", email);
        }
      })
      .catch((error) => {
        console.error("Error creating user:", error);
      });
  };

  return (
    <div>
      <Navbar />
      <div className="barber-shift-container">
        <h1>Barber Shift</h1>
        <form>
          <div className="barber-form-group">
            <label htmlFor="startTime">Start Time:</label>
            <input
              type="time"
              id="startTime"
              value={startTime}
              onChange={(e) => setStartTime(e.target.value)}
              required
            />
          </div>
          <div className="barber-form-group">
            <label htmlFor="endTime">End Time:</label>
            <input
              type="time"
              id="endTime"
              value={endTime}
              onChange={(e) => setEndTime(e.target.value)}
              required
            />
          </div>
          <div className="barber-form-group">
            <label htmlFor="dayOfWeek">Day of Week:</label>
            <select
              id="dayOfWeek"
              value={dayOfWeek}
              onChange={(e) => setDayOfWeek(e.target.value)}
              required
            >
              <option value="" disabled>
                Select a day
              </option>
              <option value="SUNDAY">Sunday</option>
              <option value="MONDAY">Monday</option>
              <option value="TUESDAY">Tuesday</option>
              <option value="WEDNESDAY">Wednesday</option>
              <option value="THURSDAY">Thursday</option>
              <option value="FRIDAY">Friday</option>
              <option value="SATURDAY">Saturday</option>
            </select>
          </div>
          <div className="related-inventory">
            <button type="button" onClick={handleSubmit}>
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default BarberShift;
