import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Features/Authentication/Login";
import Home from "./Components/Features/Home/Home";
import OffersPage from "./Components/Features/Offers&Deals/OffersPage";
import SignupPage from "./Components/Features/Authentication/Signup";
import Address from "./Components/Features/Authentication/Address";
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact path="/" element={<Login />} />
          <Route exact path="/home" element={<Home />} />
          <Route exact path="/signup" element={<SignupPage />} />
          <Route exact path="/OffersPage" element={<OffersPage />} />
          <Route exact path="/Address" element={<Address/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;