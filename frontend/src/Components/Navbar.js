import React from 'react';
<<<<<<< HEAD
import "../Components/navbar.css";
import { Link } from 'react-router-dom'

import Sidebarmenu from '../Components/Sidebarmenu';
=======
import './Navbar.css';
import { Link } from 'react-router-dom'

import Sidebarmenu from './Sidebarmenu';
>>>>>>> cef8aede988707560fbb8ab652aa155369f9bee5

const Navbar = () => {
 
  return (
    <header className = 'navbar'>
      <nav className='navbar_navigation'>
        <div></div>
        <Sidebarmenu />
<<<<<<< HEAD
=======

>>>>>>> cef8aede988707560fbb8ab652aa155369f9bee5
        <div className='helper'></div>
        <div className='nav_items'>
          <ul>
                <li><Link to='/'>Home</Link></li>
                <li><Link to='/'>Book Now</Link></li>
                <li><Link to='/'>Gallery</Link></li>
<<<<<<< HEAD
                <li><Link to='/offersPage'>Offer & Deals</Link></li>
=======
                <li><Link to='/'>Offer & Deals</Link></li>
>>>>>>> cef8aede988707560fbb8ab652aa155369f9bee5
            </ul>
        </div>
      </nav>
    </header>
    
  )
}

export default Navbar;
