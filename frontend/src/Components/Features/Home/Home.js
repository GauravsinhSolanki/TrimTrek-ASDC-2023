import React from "react";
import Navbar from "../../Navbar/Navbar";
import Footer from "../../Footer/Footer";
import "./Home.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Image1 from "../../../Assests/Title1.jpg";
import Image2 from "../../../Assests/Homepage.png";
import Image3 from "../../../Assests/TrimTrekLogo.png";

function Home() {
  return (
    <div>
      <div>
        <Navbar />
      </div>
      <main >
        <div id="myCarousel" class="carousel slide mt-2" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button
              type="button"
              data-bs-target="#myCarousel"
              data-bs-slide-to="0"
              class="active"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#myCarousel"
              data-bs-slide-to="1"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#myCarousel"
              data-bs-slide-to="2"
              aria-label="Slide 3"
            ></button>
          </div>
          <div class="carousel-inner carousel-image-wrapper">
            <div class="carousel-item active">
              <img
                src={Image1}
                class="d-block w-100 object-fit-cover"
                alt="Slide 1"
              />
            </div>
            <div class="carousel-item">
              <img
                src={Image2}
                class="d-block w-100 object-fit-cover"
                alt="Slide 2"
              />
            </div>
            <div class="carousel-item">
              <img
                src={Image3}
                class="d-block w-100 object-fit-cover"
                alt="Slide 3"
              />
            </div>
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
