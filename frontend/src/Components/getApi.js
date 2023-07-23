import axios from "axios";

const API_URL = "http://localhost:8090/";

export async function getData(url, token) {
  try {
    var myHeaders = {};
    myHeaders["Content-Type"] = "application/json";
    myHeaders["Accept"] = "application/json";
    myHeaders["Access-Control-Allow-Origin"] = "*";
    if (token) myHeaders["authorization"] = `Bearer ${token}`;

    var requestOptions = {
      headers: myHeaders,
    };

    const response = await axios.get(API_URL + url, requestOptions);
    return response;
  } catch (error) {
    console.log("error", error);
    return error;
  }
}
