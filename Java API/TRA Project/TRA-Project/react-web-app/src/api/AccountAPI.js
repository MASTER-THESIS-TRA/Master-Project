import React, {useEffect, useState} from "react";
const axios = require('axios');



export const fetchAccountInfo = ( accountID ) => {
    const [data, setData] = useState({text: []})
    useEffect(() => {
        axios.get("http://localhost:8080/api/test")
            .then((response) => {
                    return response.data
                }
            )
            .catch((error) => {
                console.log(error);
            })
    });
}

