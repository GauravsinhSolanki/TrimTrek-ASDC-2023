import React from "react";
import googlelogo from "../../../Assests/download.png";
import "./Signup.css";
import { useEffect } from "react";
import { useState } from "react";
import { postData } from "../../postApi";
import { useNavigate } from "react-router-dom";

function SignupPage() {
  useEffect(() => {
    // Hide the emailAlert element on initial load
    document.getElementById("emailError").style.display = "none";
    document.getElementById("numberError").style.display = "none";
    document.getElementById("numberAltError").style.display = "none";

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

  const [email_id, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const navigate = useNavigate();
  // const [emailError, setEmailError] = useState("");
  
  const handleNext = (e) => {
    e.preventDefault();


    const user = {
      userName: username,
      emailId: email_id,
      userPassWord: document.getElementById("user_password").value,
      gender: document.getElementById("gender").value,
      dob: document.getElementById("dob").value,
      phoneNo: document.getElementById("phone_no").value,
      altPhoneNo: document.getElementById("alt_phn_no").value,
    };

    postData(JSON.stringify(user), "/user/")
      .then((response) => {
        if (response.status === 200) {
          navigate("/address");
          console.log("User created:", response.data);
          localStorage.setItem("user_emailId", email_id);
        }
      })
      .catch((error) => {
        console.error("Error creating user:", error);
      });
  };  

  const handleEmailChange = (e) => {
    const { value } = e.target;
    validateEmail(value);
    setEmail(value);
  };

  const validateEmail = (email) => {
    // Regular expression for email validation
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (document.getElementById("email_id").value === "") {
      document.getElementById("emailError").style.display = "none";
    } else if (document.getElementById("email_id").value.match(emailRegex)) {
      document.getElementById("emailError").style.display = "none";
    } else {
      document.getElementById("emailError").style.display = "block";
    }
  };
  const validateNumber = () => {
    // Regular expression for number validation
    const phoneNumberRegex =
      /^\+?[0-9]?[-.\s]?(\([0-9]+\))?[-.\s]?[0-9]+[-.\s]?[0-9]+[-.\s]?[0-9]+$/;
    if (document.getElementById("phone_no").value === "") {
      document.getElementById("numberError").style.display = "none";
    } else if (
      document.getElementById("phone_no").value.match(phoneNumberRegex)
    ) {
      document.getElementById("numberError").style.display = "none";
    } else {
      document.getElementById("numberError").style.display = "block";
    }
    //   return emailRegex.test(email);
  };

  const validateAltNumber = () => {
    // Regular expression for number validation
    const phoneAltNumberRegex =
      /^\+?[0-9]?[-.\s]?(\([0-9]+\))?[-.\s]?[0-9]+[-.\s]?[0-9]+[-.\s]?[0-9]+$/;
    if (document.getElementById("alt_phn_no").value === "") {
      document.getElementById("numberAltError").style.display = "none";
    } else if (
      document.getElementById("alt_phn_no").value.match(phoneAltNumberRegex)
    ) {
      document.getElementById("numberAltError").style.display = "none";
    } else {
      document.getElementById("numberAltError").style.display = "block";
    }
    //   return emailRegex.test(email);
  };

  return (
    <div class="">
      <div class="m-3 ">
        <h1>Registration Page</h1>
      </div>

      <div className="container">
        <div className="row">
          <div className="col-lg-6">
            <div className="mb-3">
              <label htmlFor="user_name" class="label-align">
                Username
              </label>
              <input
                type="text"
                className="form-control"
                id="user_name"
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email_id" class="label-align">
                Email
              </label>
              <input
                type="email"
                className="form-control"
                id="email_id"
                onChange={handleEmailChange}
              />
            </div>
            <div id="emailError" class="text-danger">
              {" "}
              This email is not correct
            </div>
            <div className="mb-3">
              <label htmlFor="user_password" class="label-align">
                Password
              </label>
              <input
                type="password"
                className="form-control"
                id="user_password"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="gender" class="label-align">
                Gender
              </label>
              <select className="form-control" id="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
              </select>
            </div>
          </div>

          <div className="col-lg-6">
            <div className="mb-3">
              <label htmlFor="dob" className="label-align">
                Date of Birth
              </label>
              <input type="date" className="form-control" id="dob" />
            </div>
            <div className="mb-3">
              <label htmlFor="phone_no" class="label-align">
                Phone Number
              </label>
              <input
                type="text"
                className="form-control"
                id="phone_no"
                onChange={validateNumber}
              />
            </div>
            <div id="numberError" class="text-danger">
              {" "}
              This number is not correct
            </div>

            <div className="mb-3">
              <label htmlFor="alt_phn_no" class="label-align">
                Alternative Number
              </label>
              <input
                type="text"
                className="form-control"
                id="alt_phn_no"
                onChange={validateAltNumber}
              />
            </div>
            <div id="numberAltError" class="text-danger">
              {" "}
              This Altnumber is not correct
            </div>
            <div className="mb-3 border p-4 ">
              <img
                className="small-google-logo" // Apply the custom class here
                src={googlelogo}
                alt="Google Logo"
              />
              <button type="button" className="btn btn-light">
                Register with Google
              </button>
            </div>
            <button onClick={handleNext} className="link-button">
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default SignupPage;