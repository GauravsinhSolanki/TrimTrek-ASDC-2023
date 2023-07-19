import React, { useState } from "react";
import { Link } from "react-router-dom";
import * as Icons from "react-icons/fa";
import { Sidebar } from "./Sidebar";
import * as AiIcons from "react-icons/ai";
import "../Components/Sidebarmenu.css";
import { IconContext } from "react-icons";
export default function Sidebarmenu() {
  const [sidebar, setSidebar] = useState(false);

  const showSidebar = () => setSidebar(!sidebar);

  return (
    <IconContext.Provider value={{ color: "white" }}>
      <div className="sidebar">
        <Link to="/home" className="menu-bars1">
          <Icons.FaBars onClick={showSidebar} /> TRIM TREK
        </Link>
      </div>
      <nav className={sidebar ? "side-menu active" : "side-menu"}>
        <ul className="side-menu-items" onClick={showSidebar}>
          <li className="sidebar-toggle">
            <Link to="#" className="menu-bars">
              <AiIcons.AiOutlineClose />
            </Link>
          </li>
          {Sidebar.map((item, index) => {
            return (
              <li key={index} className={item.className}>
                <Link to={item.path}>
                  <span>{item.title}</span>
                </Link>
              </li>
            );
          })}
        </ul>
      </nav>
    </IconContext.Provider>
  );
}
