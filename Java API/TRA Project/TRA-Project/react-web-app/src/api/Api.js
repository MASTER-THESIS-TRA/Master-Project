import {useEffect, useState} from "react";

const axios = require('axios');

export const TestApi = () => {
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