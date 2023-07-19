<<<<<<< HEAD
import React from 'react';
import "../Components/navbar.css";
import { Link } from 'react-router-dom'
=======
import React from "react";
import "../Components/Navbar.css";
import { Link } from "react-router-dom";
>>>>>>> e198d79c8bd308078081f15b10576d4be6c38e44

import Sidebarmenu from "../Components/Sidebarmenu";

const Navbar = () => {
  return (
    <header className="navbar">
      <nav className="navbar_navigation">
        <div></div>
        <Sidebarmenu />
        <div className="helper"></div>
        <div className="nav_items">
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/">Book Now</Link>
            </li>
            <li>
              <Link to="/">Gallery</Link>
            </li>
            <li>
              <Link to="/offersPage">Offer & Deals</Link>
            </li>
          </ul>
        </div>
      </nav>
    </header>
  );
};

export default Navbar;
