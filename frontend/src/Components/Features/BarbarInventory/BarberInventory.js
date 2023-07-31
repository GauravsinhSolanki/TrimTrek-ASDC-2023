import { useState } from "react";
import Navbar from "../../Navbar/Navbar";
import "./BarberInventory.css";
// import { useEffect } from "react";
import { postData } from "../../postApi";
// import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
function BarberInventory() {
useEffect(() => {
 
const authToken = {
      userEmail: localStorage.getItem("user_emailId"),
    };
    postData(authToken, "/user-authentication/checkToken/")
      .then((response) => {
        if (response.status === 200) {
          if(response.data != 'true'){
            navigate("/")
          }
        } else {
        }
      })
      .catch((error) => {
        console.error("Error booking slot:", error);
      });

  }, []);
  // const navigate = useNavigate();
  
  const [productName, setProductName] = useState("");
  const [quantity, setQuantity] = useState("");
  const [description, setDescription] = useState("");
  // const [email_id, setEmail] = useState("");

  const handleNext = (e) => {
    e.preventDefault();
    const BarberInventoryData = {
      barberId: localStorage.getItem("user_emailId"),
      productName: productName,
      quantity: quantity,
      description: description,
    };

    postData(JSON.stringify(BarberInventoryData), "/barber-inventory/")
      .then((response) => {
        if (response.status === 200) {
          console.log("Inventory added:", response.data);
          localStorage.setItem("user_emailId", );
        }
      })
      .catch((error) => {
        console.error("Error creating user:", error);
      });
  };  

  return (
    <div>
      <Navbar />
      <div className="barber-inventory-container">
        <h1>Barber Inventory</h1>
        <form >
          
          <div className="barber-form-group">
            <label htmlFor="productName">Product Name:</label>
            <input
              type="text"
              id="productName"
              value={productName}
              onChange={(e) => setProductName(e.target.value)}
              required
            />
          </div>
          <div className="barber-form-group">
            <label htmlFor="quantity">Quantity:</label>
            <input
              type="number"
              id="quantity"
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
              required
            />
          </div>
          <div className="barber-form-group">
            <label htmlFor="description">Description:</label>
            <textarea
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
            />
          </div>
          <div className="related-inventory">
            <button type="submit" onClick={handleNext}>Submit</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default BarberInventory;
