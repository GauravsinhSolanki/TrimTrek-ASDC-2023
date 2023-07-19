import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from './Components/Authentication/Login';
import Home from './Components/Home';
import BarberRegister from './Components/BarberRegister';
import BarberRegisterNext from './Components/BarberRegisterNext';
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact path="/" element={<Login/>} />
          <Route exact path='/home' element={<Home/>}/>  
          <Route exact path='/BarberRegister' element={<BarberRegister/>}/>  
          <Route exact path='/BarberRegisterNext' element={<BarberRegisterNext/>}/>     
        </Routes>
      </Router>
    </div>
  );
}

export default App;
