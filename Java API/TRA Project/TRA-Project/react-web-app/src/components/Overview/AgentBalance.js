import * as React from 'react';
import {CustomTable} from "../CustomTable";
import Title from "../Title";
import {useState, useEffect} from "react";

const axios = require('axios');


export const AgentBalance = () => {
    const[rows, setRows] = useState([]);


    useEffect(async () => {
        const id = localStorage.getItem('user');
        console.log((id))
        const data = [];
        const res = await axios.get(`http://localhost:8080/overview/getBalance/${id}`)
        if(res.status != 500) {
            res.data.map((item) => {
                data.push(createData(item.type, item.amount))
            })
            setRows(data);
        }
    }, [])

    const cols = [
        { id: 'resourceType', label: 'Resource Type', minWidth: 170 },
        { id: 'amount', label: 'Amount (g)', minWidth: 170 },
    ];

    function createData(resourceType, amount) {
        return { resourceType, amount };
    }


    return (
        <div>
            <Title>List of Resources</Title>
            <CustomTable columns={cols} rows={rows} showPagination={false} maxHeight={440}/>
        </div>
    )
}