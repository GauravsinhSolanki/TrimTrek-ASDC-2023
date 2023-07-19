import React, { useState } from "react";
import "./login.css";
import logoImage from "../../Assests/TrimTrekLogo.png";
import {useNavigate } from "react-router-dom";
const Login = () => {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

 const handleLogin = (e) => {
   e.preventDefault();
  
   fetch("/api/login", {
     method: "POST",
     headers: {
       "Content-Type": "application/json",
     },
     body: JSON.stringify({
       email,
       password,
     }),
   })
     .then((response) => response.json())
     .then((data) => {
       // Handle the response from the backend
       if (data.success) {
         // Login successful, perform necessary actions
         alert("Login successful!");
         // Redirect or update application state accordingly
       } else {
         // Login failed, display error message or handle as needed
         alert("Login failed!");
       }
     })
     .catch((error) => {
       // Handle any error that occurred during the API call
       console.error("Error:", error);
     });
 };


  const handleForgotPassword = (e) => {
    e.preventDefault();
    // Handle forgot password logic here
    alert("Forgot password link clicked!");
  };

  const handleSignup = (e) => {
    e.preventDefault();
    navigate("/signup")
  };

  return (
    <div className="login-container">
      <div className="image-container">
        <img
          src={logoImage}
          alt="Barber Login Page"
        />
      </div>
      <div className="form-container">
        <h1>TrimTrek</h1>
        <form onSubmit={handleLogin}>
          <h2>Login here</h2>
          <input
            type="email"
            id="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            id="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Login</button>
          <div className="form-links">
            <button onClick={handleForgotPassword} className="link-button">
              Forgot Password
            </button>

            <button onClick={handleSignup} className="link-button">
              Signup
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
