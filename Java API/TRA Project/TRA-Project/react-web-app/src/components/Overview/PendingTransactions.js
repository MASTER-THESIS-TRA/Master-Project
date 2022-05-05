import * as React from 'react';
import {CustomTable} from "../CustomTable";
import Title from "../Title";

const axios = require('axios');
const apicall = () => {
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
    'Type', 'Date', 'Net'
];

const rows = [
    ['Transfer', '13/04-2022', '-40 Coffee Bag (500g), +200$'],
    ['Transform', '03/04-2022', '-20 Roasted Coffee (kg), -40 Paper Bag (pcs), +40 Coffee Bag (500g)'],
    ['Transfer', '01/04-2022', '-10$, +40 Paper Bag (pcs)'],
    ['Transfer', '01/04-2022', '-100$, +20 Roasted Coffee (kg)']
];

export const PendingTransactions = () => {
    return (
        <div>
            <Title>Pending Transactions</Title>
            <CustomTable cols={cols} rows={rows} />
        </div>
    )
}