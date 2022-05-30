import axios from "axios";
import {instanceOf} from "prop-types";


export const ValidateTransfer = (senderData, receiverData ) => {
    let data = [senderData, receiverData]
    axios.post('http://localhost:8080/api/validateTransfer', data)
        .then((response) =>
            console.log("Fetched: ", response.data)
        )
        .catch((error) => {
            alert("AN ERROR: " + error)
        })
}