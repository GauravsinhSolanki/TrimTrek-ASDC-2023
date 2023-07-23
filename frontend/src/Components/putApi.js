import axios from "axios";

const API_URL = "http://localhost:8080/api";

export async function putData(body, url, token) {
  try {
    var myHeaders = {};
    myHeaders["Content-Type"] = "application/json";
    myHeaders["Accept"] = "application/json";
    myHeaders["Access-Control-Allow-Origin"] = "*";
    if (token) myHeaders["authorization"] = `Bearer ${token}`;

    var requestOptions = {
      headers: myHeaders,
    };

    const response = await axios.put(API_URL + url, body, requestOptions);
    return response;
  } catch (error) {
    console.log("error", error);
    return error;
  }
}
