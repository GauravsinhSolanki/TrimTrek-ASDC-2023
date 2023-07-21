import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Authentication/Login";
import Home from "./Components/Home";
import OffersPage from "./Components/OffersPage";
import SignupPage from "./Components/Authentication/Signup";
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact path="/" element={<Login />} />
          <Route exact path="/home" element={<Home />} />
          <Route exact path="/Signup" element={<SignupPage/>} />
          <Route exact path="/OffersPage" element={<OffersPage/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
