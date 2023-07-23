import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
      <App />
    <link rel="preconnect" href="https://fonts.googleapis.com"></link>,
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin></link>,
    <link
      href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@300;900&display=swap"
      rel="stylesheet"
    ></link>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
