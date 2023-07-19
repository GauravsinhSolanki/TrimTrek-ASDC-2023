<<<<<<< HEAD
import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Authentication/Login";
import Home from "./Components/Home";
import BarberRegister from "./Components/BarberRegister";
import Navbar from "./Components/Navbar";
import SignupPage from "./Components/Authentication/Signup";
import OffersPage from "./Components/offerPage";
=======
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from './Components/Authentication/Login';
import Home from './Components/Home';
import BarberRegister from './Components/BarberRegister';
import BarberRegisterNext from './Components/BarberRegisterNext';
>>>>>>> cef8aede988707560fbb8ab652aa155369f9bee5
function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
<<<<<<< HEAD
          <Route exact path="/" element={<Login />} />
          <Route exact path="/home" element={<Home />} />
          <Route exact path="/signup" element={<SignupPage />} />
          <Route exact path="/BarberRegister" element={<BarberRegister />} />
          <Route exact path="/offersPage" element={<OffersPage />} />
=======
          <Route exact path="/" element={<Login/>} />
          <Route exact path='/home' element={<Home/>}/>  
          <Route exact path='/BarberRegister' element={<BarberRegister/>}/>  
          <Route exact path='/BarberRegisterNext' element={<BarberRegisterNext/>}/>     
>>>>>>> cef8aede988707560fbb8ab652aa155369f9bee5
        </Routes>
      </Router>
    </div>
  );
}

export default App;
