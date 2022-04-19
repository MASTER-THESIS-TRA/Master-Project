import * as React from 'react';
import {CustomTable} from "../CustomTable";

const axios = require('axios');

function createData(
    name,
    calories,
    fat,
    carbs,
    protein,
) {
    return [ name, calories, fat, carbs, protein ];
}

const rows = () => {
    console.log("start")
     axios.get('http://localhost:8080/pendingTransactions')
        .then((response) => {
            console.log("response:");
            console.log(response.data);
        })
         .catch((error) => {
             console.log("error:");
             console.log(error)
         });
}

const cols = [
    'Name', 'Calories', 'Fat', 'Carbs', 'protein'
];


export const PendingTransactions = () => {
    return (
        <div>
            <button onClick={rows()}>Fetch data</button>
        </div>
    )
}