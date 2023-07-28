import React, { useState } from "react";
import "./AddHoliday.css";
import Navbar from "../../Navbar/Navbar";

const AddHoliday = () => {
  const [holiday, setHoliday] = useState({
    holidayId: "",
    festivalDate: "",
    festivalName: "",
  });

  const festivalNames = [
    "Ram navami",
    "Navratri",
    "Diwali",
    "RAKSHABANDHAN",
    "RATHAYATRA",
    // Add more festival names as needed
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setHoliday((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Here you can handle the submission of the holiday data
    // For example, you can send it to the backend API or perform any other actions.
    console.log("Holiday data to submit:", holiday);
    // Reset the form after submission (optional)
    setHoliday({
      holidayId: "",
      festivalDate: "",
      festivalName: "",
    });
  };

  return (
    <div>
      <Navbar />
      <div className="add-holiday-container"> {/* Add a class for the container */}
        <h1 className="add-holiday-title">Add Holiday</h1> {/* Add a class for the title */}
        <form onSubmit={handleSubmit} className="add-holiday-form"> {/* Add a class for the form */}
          <div className="add-holiday-group"> {/* Add a class for the group */}
            <label>
              Holiday:
              <select
                name="holidayId"
                value={holiday.holidayId}
                onChange={handleChange}
              >
                <option value="">Select a festival</option>
                {festivalNames.map((festival, index) => (
                  <option key={index} value={festival}>
                    {festival}
                  </option>
                ))}
              </select>
            </label>
          </div>
          <div className="add-holiday-group"> {/* Add a class for the group */}
            <label>
              Festival Date:
              <input
                type="date"
                name="festivalDate"
                value={holiday.festivalDate}
                onChange={handleChange}
              />
            </label>
          </div>
          <div className="add-holiday-group"> {/* Add a class for the group */}
            <label>
              Festival Name:
              <input
                type="text"
                name="festivalName"
                value={holiday.festivalName}
                onChange={handleChange}
              />
            </label>
          </div>
          <button type="submit" className="add-holiday-submit">Submit</button> {/* Add a class for the submit button */}
        </form>
      </div>
    </div>
  );
};

export default AddHoliday;
