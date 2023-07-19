import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './BarberRegister.css';
import Navbar from "./Navbar";
const BarberRegister = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [birthdate, setBirthdate] = useState('');
  const [address, setAddress] = useState('');
  const navigate = useNavigate();
  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleBirthdateChange = (e) => {
    setBirthdate(e.target.value);
  };

  const handleAddressChange = (e) => {
    setAddress(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your form submission logic here
    // This is just a sample, you can replace it with your own logic
    console.log('Form submitted:', { name, email, password, birthdate, address });
    navigate('/BarberRegisterNext');
  };

  const handleBackToHomepage = () => {
    // Redirect to the homepage
    navigate('/home');
  };

  return (
    <div>
      <div>
        <Navbar />
      </div>
      <div className="signup-container">
        <h1>Register as Barber</h1>
        <form onSubmit={handleSubmit}>
          <div className="form-field">
            <label htmlFor="name">Name:</label>
            <input type="text" id="name" value={name} onChange={handleNameChange} required />
          </div>
          <div className="form-field">
            <label htmlFor="email">Email:</label>
            <input type="email" id="email" value={email} onChange={handleEmailChange} required />
          </div>
          <div className="form-field">
            <label htmlFor="password">Password:</label>
            <input type="password" id="password" value={password} onChange={handlePasswordChange} required />
          </div>
          <div className="form-field">
            <label htmlFor="birthdate">Birthdate:</label>
            <input type="date" id="birthdate" value={birthdate} onChange={handleBirthdateChange} required />
          </div>
          <div className="form-field">
            <label htmlFor="address">Address:</label>
            <input type="text" id="address" value={address} onChange={handleAddressChange} required />
          </div>
          <div className="form-buttons">
            <button type="button" onClick={handleBackToHomepage}>Back to Homepage</button>
            <button type="submit">Next Page</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default BarberRegister;
