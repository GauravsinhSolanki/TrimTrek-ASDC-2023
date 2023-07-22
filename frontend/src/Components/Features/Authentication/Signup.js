import React from "react";
import googlelogo from "../../../Assests/download.png" 
import './Signup.css'
import { useEffect } from "react";
import { useState } from "react";
function SignupPage(){
    useEffect(() => {
        // Hide the emailAlert element on initial load
        document.getElementById("emailError").style.display = 'none';
        document.getElementById("numberError").style.display = 'none';
        document.getElementById("numberAltError").style.display = 'none';
        document.getElementById("confirmPasswordAlert").style.display = 'none';
      }, []);

    const [email, setEmail] = useState("");
    const [emailError, setEmailError] = useState("");
  
    const handleEmailChange = (e) => {
      const { value } = e.target;
      validateEmail(value);
      setEmail(value);
 
    };
  
    const validateEmail = (email) => {
      // Regular expression for email validation
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      if(document.getElementById("email").value===''){
        document.getElementById("emailError").style.display = 'none';
    }
    else
    if(document.getElementById("email").value.match(emailRegex)){
        document.getElementById("emailError").style.display = 'none';
    }
    else{
        document.getElementById("emailError").style.display = 'block';
    }
}
    const validateNumber = () => {
        

        // Regular expression for number validation
     const phoneNumberRegex =  /^\+?[0-9]?[-.\s]?(\([0-9]+\))?[-.\s]?[0-9]+[-.\s]?[0-9]+[-.\s]?[0-9]+$/ ;
        if(document.getElementById("phoneNumber").value===''){
          document.getElementById("numberError").style.display = 'none';
      }
      else
      if(document.getElementById("phoneNumber").value.match(phoneNumberRegex)){
          document.getElementById("numberError").style.display = 'none';
      }
      else{
          document.getElementById("numberError").style.display = 'block';
      }
    //   return emailRegex.test(email);
    };
    
    const validateAltNumber = () => {

        // Regular expression for number validation
        const phoneAltNumberRegex =  /^\+?[0-9]?[-.\s]?(\([0-9]+\))?[-.\s]?[0-9]+[-.\s]?[0-9]+[-.\s]?[0-9]+$/ ;
        if(document.getElementById("phoneAltNumber").value===''){
          document.getElementById("numberAltError").style.display = 'none';
        }
        else
        if(document.getElementById("phoneAltNumber").value.match(phoneAltNumberRegex)){
          document.getElementById("numberAltError").style.display = 'none';
        }
        else{
          document.getElementById("numberAltError").style.display = 'block';
        }
        //   return emailRegex.test(email);
    };

    const validateMatchPassword = () => {
     

        // Regular expression for number validation
        if(document.getElementById("password").value==='' || document.getElementById("confirmPassword").value==='' ){
          document.getElementById("confirmPasswordAlert").style.display = 'none';
      }
      else
      if(document.getElementById("password").value===document.getElementById("confirmPassword").value){
          document.getElementById("confirmPasswordAlert").style.display = 'none';
      }
      else{
          document.getElementById("confirmPasswordAlert").style.display = 'block';
      }
    //   return emailRegex.test(email);
    };
    return(
        <div class="">
            <div class="m-3 ">      
                <h1>Registration Page</h1>
            </div>
 
      <div className="container">
        <div className="row">
          <div className="col-lg-6">
            
              <div className="mb-3">
                <label htmlFor="username" class = "label-align">Username</label>
                <input type="text" className="form-control" id="username" />
              </div>
              <div className="mb-3">
                <label htmlFor="email" class = "label-align">Email</label>
                <input type="email" className="form-control" id="email" onChange={handleEmailChange} />
              </div>
              <div id='emailError' class='text-danger'> This email is not correct</div>
              <div className="mb-3">
                <label htmlFor="password" class = "label-align">Password</label>
                <input type="password" className="form-control" id="password" />
              </div>
              <div className="mb-3">
                <label htmlFor="password" class = "label-align">Confirm Password</label>
                <input type="password" className="form-control" id="confirmPassword" onChange={validateMatchPassword}/>
              </div>
              <div id='confirmPasswordAlert' class='text-danger'> This password are not same</div>
              <div className="mb-3">
                <label htmlFor="gender">Gender</label>
                <select className="form-control" id="gender">
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                  <option value="other">Other</option>
                </select>
              </div>
           
            
          </div>
  
          <div className="col-lg-6">
            <div className="mb-3">
                <label htmlFor="dob" className="label-align">Date of Birth</label>
                <input type="date" className="form-control" id="dob" />
              </div>
              <div className="mb-3">
                <label htmlFor="phoneNumber" class = "label-align" >Phone Number</label>
                <input type="text" className="form-control" id="phoneNumber" onChange={validateNumber} />
              </div>
              <div id='numberError' class='text-danger'> This number is not correct</div>

              <div className="mb-3">
                <label htmlFor="altNumber" class = "label-align">Alternative Number</label>
                <input type="text" className="form-control" id="phoneAltNumber" onChange={validateAltNumber}/>
              </div>
              <div id='numberAltError' class='text-danger'> This Altnumber is not correct</div>
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
          </div>
        </div>
      </div>
      </div>

    );

}
export default SignupPage;