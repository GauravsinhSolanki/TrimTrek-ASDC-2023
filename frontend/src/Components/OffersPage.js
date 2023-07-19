import React from "react";
import "../Components/OffersPage.css";
import Navbar from "./Navbar";

const OffersPage = () => {
  const offers = [
    {
      id: 1,
      title: "Summer Special",
      description:
        "Get 20% off on all haircuts and styling during the summer season. Offer valid from June 1st to August 31st.",
    },
    {
      id: 2,
      title: "Weekend Bonanza",
      description:
        "Enjoy exclusive discounts on weekends. Get a free beard trim with every haircut on Saturdays and Sundays.",
    },
    {
      id: 3,
      title: "Refer a Friend",
      description:
        "Refer a friend and get 50% off on your next haircut. Your friend will also receive 20% off on their first visit.",
    },
    {
      id: 4,
      title: "Senior Citizens Discount",
      description:
        "Seniors aged 60 and above can avail a 10% discount on all services from Monday to Thursday.",
    },
    {
      id: 5,
      title: "Student Special",
      description:
        "Show your valid student ID and get 15% off on all haircuts and styling. Valid from Monday to Friday.",
    },
    {
      id: 6,
      title: "Birthday Treat",
      description:
        "Celebrate your birthday with us and receive a complimentary haircut or styling session as a gift from us.",
    },
  ];

  return (
      <div className="offers-container">
        <Navbar/>
      <h1 className="offers-title">Offers and Deals</h1>
      <div className="offers-list">
        {offers.map((offer) => (
          <div key={offer.id} className="offer-card">
            <h2 className="offer-title">{offer.title}</h2>
            <p className="offer-description">{offer.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default OffersPage;
