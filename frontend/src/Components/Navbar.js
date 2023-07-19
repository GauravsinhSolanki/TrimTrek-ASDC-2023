import React from "react";
import "../Components/Navbar.css";
import { Link } from "react-router-dom";

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
