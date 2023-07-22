import React, { useState } from "react";
import "./Signup.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
const SignupPage = () => {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [gender, setGender] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [alternativeNumber, setAlternativeNumber] = useState("");
  const [errors, setErrors] = useState({});

  const validateForm = () => {
    let errors = {};

    // Validate user name
    if (!userName.trim()) {
      errors.userName = "User name is required";
    }

    // Validate email
    if (!email.trim()) {
      errors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      errors.email = "Invalid email format";
    }

    // Validate password
    if (!password.trim()) {
      errors.password = "Password is required";
    } else if (password.length < 6) {
      errors.password = "Password must be at least 6 characters long";
    }


    // Validate gender
    if (!gender.trim()) {
      errors.gender = "Gender is required";
    }

    // Validate date of birth
    if (!dateOfBirth || !(dateOfBirth instanceof Date) || isNaN(dateOfBirth)) {
      errors.dateOfBirth = "Date of Birth is required";
    }

    

    // Validate phone number
    if (!phoneNumber.trim()) {
      errors.phoneNumber = "Phone Number is required";
    }

    // Validate alternative number
    if (!alternativeNumber.trim()) {
      errors.alternativeNumber = "Alternative Number is required";
    }

    setErrors(errors);

    return Object.keys(errors).length === 0;
  };

  const handleSignup = (e) => {
    e.preventDefault();

    if (validateForm()) {
      // const formattedDateOfBirth = dateOfBirth.toISOString().split("T")[0];

      console.log("Signup form submitted");
      console.log("User Name:", userName);
      console.log("Email:", email);
      console.log("Password:", password); 
      console.log("Gender:", gender);
      console.log("Date of Birth:", setDateOfBirth);
      console.log("Phone Number:", phoneNumber);
      console.log("Alternative Number:", alternativeNumber);
      // Perform signup logic here
    }
  };

  const handleGoogleSignup = () => {
    // Handle Google Sign-In logic here
  };

  return (
    <div className="signup-container">
      <div className="form-container-signup">
        <h1>Sign Up</h1>
        <form onSubmit={handleSignup}>
          <div>
            <label htmlFor="userName">User Name</label>
            <input
              type="text"
              id="userName"
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
            />
            {errors.userName && <p className="error">{errors.userName}</p>}
          </div>
          <div>
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {errors.email && <p className="error">{errors.email}</p>}
          </div>
          <div>
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {errors.password && <p className="error">{errors.password}</p>}
          </div>
          <div>
            <label htmlFor="gender">Gender</label>
            <select
              id="gender"
              value={gender}
              onChange={(e) => setGender(e.target.value)}
            >
              <option value="">Select Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
            </select>
            {errors.gender && <p className="error">{errors.gender}</p>}
          </div>
          <div>
            <label htmlFor="dateOfBirth">Date of Birth</label>
            <DatePicker
              id="dateOfBirth"
              selected={dateOfBirth}
              onChange={(date) => setDateOfBirth(date)}
              dateFormat="yyyy-MM-dd"
              className="date-picker"
            />
            {/* Add any date picker specific styles here */}
          </div>
          <div>
            <label htmlFor="phoneNumber">Phone Number</label>
            <input
              type="text"
              id="phoneNumber"
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
            />
            {errors.phoneNumber && (
              <p className="error">{errors.phoneNumber}</p>
            )}
          </div>
          <div>
            <label htmlFor="alternativeNumber">Alternative Number</label>
            <input
              type="text"
              id="alternativeNumber"
              value={alternativeNumber}
              onChange={(e) => setAlternativeNumber(e.target.value)}
            />
            {errors.alternativeNumber && (
              <p className="error">{errors.alternativeNumber}</p>
            )}
          </div>
          <button type="submit">Next &#62; &#62;</button>
        </form>
        <div className="oauth-container">
          <p className="oauth-text">Or sign up with:</p>
          <button className="google-signup" onClick={handleGoogleSignup}>
            <span className="google-icon"></span>
            Google
          </button>
        </div>
      </div>
    </div>
  );
};

export default SignupPage;
