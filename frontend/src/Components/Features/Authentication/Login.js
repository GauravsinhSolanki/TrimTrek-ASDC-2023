import React, { useState } from "react";
import "./login.css";
import logoImage from "../../../Assests/TrimTrekLogo.png";
import { useNavigate } from "react-router-dom";
import { getData } from "../../getApi";

const Login = () => {
  const [emailId, setEmail] = useState();
  const [userPassWord, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    const url = `user/sign-in-by-/${emailId}/${userPassWord}`;

    getData(url, {})
      .then((response) => {
        if (response.status === 200) {
          console.log("User created:", response.data);
          navigate("/home");
        } else {
          alert("Invalid Credtial");
        }
      })
      .catch((error) => {
        console.error("Error creating user:", error);
      });
  };

  const handleEmail = (e) => {
    e.preventDefault();
    const { value } = e.target;
    setEmail(value);
  };
  const handlePassword = (e) => {
    e.preventDefault();
    const { value } = e.target;
    setPassword(value);
  };

  const handleForgotPassword = (e) => {
    e.preventDefault();
    alert("Forgot password link clicked!");
  };

  const handleSignup = (e) => {
    e.preventDefault();
    navigate("/signup");
  };

  return (
    <div className="login-container">
      <div className="image-container">
        <img src={logoImage} alt="Barber Login Page" />
      </div>
      <div className="form-container">
        <h1>TrimTrek</h1>
        <form onSubmit={handleLogin}>
          <h2>Login here</h2>
          <input
            type="email"
            id="emailId"
            placeholder="Email"
            value={emailId}
            onChange={handleEmail}
            required
          />
          <input
            type="password"
            id="password"
            placeholder="Password"
            value={userPassWord}
            onChange={handlePassword}
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