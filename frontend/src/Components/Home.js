import React from "react";
import Navbar from "./Navbar";
//import Footer from "./Footer";
import './Home.css';
function Home() {
  return (
    <div>
      <div>
        <Navbar />
      </div>
      <main style = {{marginTop: '64px'}}>
        <div className="home_container">
          <div className="image_logo">
            <p>Trim Trek</p>
          </div>

          <div className="image_text">
            <p>Choose your Perfect Cut</p>
          </div>
          
          
        </div>
      </main>
      {/* <div>
        <Footer />
      </div> */}

    </div>
  );
}

export default Home;
