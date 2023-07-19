import React from 'react';
import './App.css';

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from './Components/Authentication/Login';
import Home from './Components/Home';
import BarberRegister from './Components/BarberRegister';
import BarberRegisterNext from './Components/BarberRegister';
import Navbar from './Components/Navbar';
import SignupPage from './Components/Authentication/Signup';
import OffersPage from './Components/OffersPage'; // Import the OffersPage component

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Login />} />
          <Route exact path="/home" element={<Home />} />
          <Route exact path="/signup" element={<SignupPage />} />
          <Route exact path="/BarberRegister" element={<BarberRegister />} />
          <Route exact path="/offersPage" element={<OffersPage />} /> {/* Include the OffersPage component */}
          <Route exact path="/" element={<Login />} />
          <Route exact path='/home' element={<Home />} />  
          <Route exact path='/BarberRegister' element={<BarberRegister />} />  
          <Route exact path='/BarberRegisterNext' element={<BarberRegisterNext />} />     
        </Routes>
      </Router>
    </div>
  );
}

export default App;
