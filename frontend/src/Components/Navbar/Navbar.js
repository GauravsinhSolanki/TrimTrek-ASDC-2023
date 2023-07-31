import React from "react";
import "./Navbar.css";
import { Link } from "react-router-dom";
import { useNavigate} from "react-router-dom";

import { postData } from "../postApi";
import Sidebarmenu from "../Slidbar/Sidebarmenu";

const handleLogout=()=>{
  const authToken = {
    userEmail: localStorage.getItem("user_emailId"),
  };
  postData(authToken, "/user-authentication/deleteToken/").then((response) => {
      if (response.status === 200) {
        if(response.data != 'true'){
          window.location.href="/"
        }
      } else {
      }
    }).catch((error) => {
      console.error("Error booking slot:", error);
    });
}

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
            <li>
              <Link onClick={handleLogout}>Logout</Link>
            </li>
          </ul>
        </div>
      </nav>
    </header>
  );
};

export default Navbar;
