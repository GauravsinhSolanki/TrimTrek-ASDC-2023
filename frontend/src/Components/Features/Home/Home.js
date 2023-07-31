import React from "react";
import Navbar from "../../Navbar/Navbar";
import Footer from "../../Footer/Footer";
import "./Home.css";
// import { Carousel } from "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import Image1 from "../../../Assests/Title1.jpg";
import Coloumn1 from "../../../Assests/Img1.jpg";
import Coloumn2 from "../../../Assests/Img2.jpg";
import Coloumn3 from "../../../Assests/Img3.jpg";
import Card2 from "../../../Assests/Card2.jpg";
import Card3 from "../../../Assests/Card3.jpg";
import { useEffect } from "react";
import { postData } from "../../postApi";

function Home() {
useEffect(() => {
 
const authToken = {
      userEmail: localStorage.getItem("user_emailId"),
    };
    postData(authToken, "/user-authentication/checkToken/")
      .then((response) => {
        if (response.status === 200) {
          if(response.data !== 'true'){
            window.location.href="/"
          }
        } else {
        }
      })
      .catch((error) => {
        console.error("Error booking slot:", error);
      });

  }, []);
  return (
    <div>
      <div>
        <Navbar />
      </div>
      <main>
        <div
          id="myCarousel"
          class="carousel slide mt-2"
          data-bs-ride="carousel"
        >
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
                src={Coloumn1}
                class="d-block w-100 object-fit-cover"
                alt="Barber slide 1"
              />
            </div>
            <div class="carousel-item">
              <img
                src={Coloumn2}
                class="d-block w-100 object-fit-cover"
                alt="Barber Slide 2"
              />
            </div>
            <div class="carousel-item">
              <img
                src={Coloumn3}
                class="d-block w-100 object-fit-cover"
                alt="Barber Slide 3"
              />
            </div>
          </div>
        </div>
        <div className="container mt-4">
          <div className="row">
            <div className="col-md-3">
              <img
                src={Image1}
                className="img-fluid rounded"
                alt="Barber Img 3"
              />
            </div>
            <div className="col-md-7 mt-3  d-flex align-items-centre">
              <div className="card-body ">
                <h5 className="card-title fw-bold">Be a Barber</h5>
                <p className="card-text">
                  Ready to take your barbering skills to the next level? Join
                  our team of talented barbers and be part of a thriving
                  community that values craftsmanship and creativity. As a
                  member of TrimTrek, you'll have the opportunity to showcase
                  your expertise, connect with clients from all walks of life,
                  and grow your career in the world of grooming and styling.
                  Take the first step towards becoming a valued barber and
                  register with us today.
                </p>
                <a href="/barberRegister" className="btn btn-primary">
                  Register as a Barber
                </a>
              </div>
            </div>
            <div className="col-md-3 mt-3">
              <img
                src={Card2}
                className="img-fluid rounded"
                alt="Barber Img 1"
              />
            </div>
            <div className="col-md-7 mt-3 d-flex align-items-centre">
              <div className="card-body ">
                <h5 className="card-title fw-bold">See our Gallary</h5>
                <p className="card-text">
                  Explore our diverse collection of expertly crafted hairstyles.
                  Whether you're looking for a classic cut, a trendy style, or
                  something unique, our gallery has it all. Our skilled barbers
                  create stunning looks that leave a lasting impression.
                  Discover the perfect hairstyle for every occasion and get
                  inspired for your next visit.
                </p>
                <a href="/gallary" className="btn btn-primary">
                  Photo Gallary
                </a>
              </div>
            </div>
            <div className="col-md-3 mt-3">
              <img
                src={Card3}
                className="img-fluid rounded"
                alt="Barber Img 2"
              />
            </div>
            <div className="col-md-7 mt-3 d-flex align-items-centre">
              <div className="card-body ">
                <h5 className="card-title fw-bold">Book an Appointment</h5>
                <p className="appointment-text">
                  Schedule your next grooming session with ease! Our
                  user-friendly appointment scheduling system allows you to book
                  your preferred date and time with one of our skilled barbers.
                  Whether it's a haircut, shave, or a grooming treatment, we're
                  here to make you look your best. Take the first step towards
                  your fresh new look by reserving your spot now.
                </p>

                <a href="/gallary" className="btn btn-primary">
                  Photo Gallary
                </a>
              </div>
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
