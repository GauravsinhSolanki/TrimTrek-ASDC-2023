import React from "react";
import "../Components/Footer.css";

const Footer = () => {
  const currentYear = new Date().getFullYear();

  return (
    <footer>
      <div className="footer-column">
        <p className="footer-text">TrimTrack &copy; {currentYear}</p>
      </div>
    </footer>
  );
};

export default Footer;
