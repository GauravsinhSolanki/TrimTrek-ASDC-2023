import React, { useState } from "react";
import "./login.css";
import logoImage from "../../Assests/TrimTrekLogo.png";
const Login = () => {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();
    // Perform login logic here
    alert("Login successful!");
  };

  const handleForgotPassword = (e) => {
    e.preventDefault();
    // Handle forgot password logic here
    alert("Forgot password link clicked!");
  };

  const handleSignup = (e) => {
    e.preventDefault();
    // Handle signup logic here
    alert("Signup link clicked!");
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
