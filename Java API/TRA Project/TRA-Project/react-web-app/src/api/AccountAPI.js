import React, {useEffect, useState} from "react";
const axios = require('axios');



export const fetchAccountInfo = () => {
    axios.get("http://localhost:8080/api/test")
        .then((response) => {
                console.log(response.data)
            }
        )
        .catch((error) => {
            console.log(error);
        })
}

