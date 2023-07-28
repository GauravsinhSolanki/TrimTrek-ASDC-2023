import React, { useState } from "react";
import "./login.css";
import logoImage from "../../../Assests/TrimTrekLogo.png";
import { useNavigate, Link } from "react-router-dom";
import { getData } from "../../getApi";
import { postData } from "../../postApi";

const Login = () => {
  const [emailId, setEmail] = useState("");
  const [userPassWord, setPassword] = useState("");
  const navigate = useNavigate();

  const handleCustomerLogin = async (e) => {
    e.preventDefault();

    const url = `user/sign-in-by-/${emailId}/${userPassWord}`;

    getData(url, {})
      .then((response) => {
        if (response.status === 200) {
          console.log("Customer logged in:", response.data);
          navigate("/home");
        } else {
          alert("Invalid Credential");
        }
      }).catch((error) => {
        console.error("Error logging in:", error);
      });
      console.log("outside")
    const roleData = {
      userId : localStorage.getItem("user_emailId"),
      roleId : ["customer-id"]
    }
    postData(JSON.stringify(roleData), "/user-role/")
    .then((response) => {
      if (response.status === 200) {
        console.log("Role added:", response.data);
      }
    })
    .catch((error) => {
      console.error("Error creating user:", error);
    });

      
  };

  const handleBarberLogin = async (e) => {
    e.preventDefault();

    const url = `user/sign-in-by-/${emailId}/${userPassWord}`;

    getData(url, {})
      .then((response) => {
        if (response.status === 200) {
          console.log("Barber logged in:", response.data);
          navigate("/barberhome");
        } else {
          alert("Invalid Credential");
        }
      })
      .catch((error) => {
        console.error("Error logging in:", error);
      });

      const roleData = {
        userId : localStorage.getItem("user_emailId"),
        roleId : ["barber-id"]
      }
      postData(JSON.stringify(roleData), "/user-role/")
      .then((response) => {
        if (response.status === 200) {
          console.log("Role added:", response.data);
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
        <form>
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
          <div className="login-buttons">
            <button className="login-customer-button" onClick={handleCustomerLogin}>Login as Customer</button>

            <Link to="/barber-registration" onClick={handleBarberLogin} className="barber-register-link">Register as Barber</Link>
          </div>

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
