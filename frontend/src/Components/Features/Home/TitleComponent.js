import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
    

function TileComponent(props) {
  return (
    <div>
      <div class="card md-4 shadow p-3 mb-5 bg-white rounded">
        <div className="ratio ratio-1x1">
          <img
            src={props.imgSource}
            class=" img-thumbnail object-fit-cover "
            alt="Product 1"
          />
        </div>

        <div class="card-body pt-10 mt-10">
          <h5 class="card-title">{props.productTitle}</h5>
          <p class="card-text">$19.99</p>
          <p class="card-text">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          </p>
          <a href="/login" class="btn-primary">
            Buy Now
          </a>
        </div>
      </div>
    </div>
  );
}
export default TileComponent;
