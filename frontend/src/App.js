import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Features/Authentication/Login";
import Home from "./Components/Features/Home/Home";
import OffersPage from "./Components/Features/Offers&Deals/OffersPage";
import SignupPage from "./Components/Features/Authentication/Signup";
import Address from "./Components/Features/Authentication/Address";
import BarberHome from "./Components/Features/Home/BarberHome";
import BarberHoliday from "./Components/Features/Holiday/AddHoliday";
import BarberShift from "./Components/Features/BarberShift/BarberShift";
import AppointmentBooking from "./Components/Features/Appointment/BookAppointment"
import BarberInventory from "./Components/Features/BarbarInventory/BarberInventory";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
        <Route exact path="/" element={<Login />} />
          <Route exact path="/home/" element={<Home />} />
          <Route exact path="/signup" element={<SignupPage />} />
          <Route exact path="/OffersPage" element={<OffersPage />} />
          <Route exact path="/Address" element={<Address/>} />
          <Route exact path="/barberhome" element={<BarberHome/>} />
          <Route exact path="/barberinventory" element={<BarberInventory/>} />
          <Route exact path="/barberholiday" element={<BarberHoliday/>} />
          <Route exact path="/barbershift" element={<BarberShift/>} />
          <Route exact path="/appointmentbooking" element={<AppointmentBooking/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;