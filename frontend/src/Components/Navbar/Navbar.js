import React from "react";
import "./Navbar.css";
import { Link } from "react-router-dom";

import Sidebarmenu from "../Slidbar/Sidebarmenu";

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
              <Link to="/Home">Home</Link>
            </li>
            <li>
              <Link to="/appointmentbooking">Book Now</Link>
            </li>
            <li>
              <Link to="/">Gallery</Link>
            </li>
            <li>
              <Link to="/OffersPage">Offer & Deals</Link>
            </li>
          </ul>
        </div>
      </nav>
    </header>
  );
};

export default Navbar;
