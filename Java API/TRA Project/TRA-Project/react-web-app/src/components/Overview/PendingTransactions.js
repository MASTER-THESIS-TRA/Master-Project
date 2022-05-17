import * as React from 'react';
import {CustomTable} from "../CustomTable";
import Title from "../Title";
import {useState, useEffect} from "react";

const axios = require('axios');

const cols = [
    { id: 'type', label: 'Type', minWidth: 170 },
    { id: 'date', label: 'Date', minWidth: 170 },
    { id: 'net', label: 'Net', minWidth: 170 },
];

function createData(type, date, net) {
    return { type, date, net };
}

const rows = [
    createData('Transfer', '13/04-2022', '-40 Coffee Bag (500g), +200$'),
    createData('Transform', '03/04-2022', '-20 Roasted Coffee (kg), -40 Paper Bag (pcs), +40 Coffee Bag (500g)'),
    createData('Transfer', '01/04-2022', '-10$, +40 Paper Bag (pcs)'),
    createData('Transfer', '01/04-2022', '-100$, +20 Roasted Coffee (kg)')
];

export const PendingTransactions = () => {
    //const[cols, setCols] = useState();
    //const[rows, setRows] = useState();

    /*
    useEffect(() => {
        fetchPendingTransactions ()
    }, [])

    const fetchPendingTransactions = () => {
        axios.get("http://localhost:8080")
            .then((response) => {
                    setCols(response.data)
                //  setRows(response.data)
                    //console.log(response.data.json())
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }

     */

    return (
        <div>
            <Title>Pending Transactions</Title>
            <CustomTable columns={cols} rows={rows} showPagination={false} maxHeight={440}/>
        </div>
    )
}