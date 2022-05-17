import React, {useEffect, useState} from "react";
const axios = require('axios');



export const fetchAccountInfo = () => {
    axios.get("http://localhost:8080/user/getUserInfo")
        .then((response) => {
                return response.data.json()
            }
        )
        .catch((error) => {
            console.log(error);
        })
}

