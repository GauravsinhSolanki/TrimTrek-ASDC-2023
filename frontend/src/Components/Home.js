import React from "react";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import "../Components/Home.css";
function Home() {
  return (
    <div>
      <div>
        <Navbar />
      </div>
      <main style={{ marginTop: "60px" }}>
        <div className="home_container">
          <div className="image_logo">
            <p className="img-text">Trim Trek</p>
          </div>
          <div>
            <p className="img-text">Choose your Perfect Cut</p>
          </div>
        </div>
      </main>
      <div>
        <Footer />
      </div>
    </div>
  );
}

export default Home;
