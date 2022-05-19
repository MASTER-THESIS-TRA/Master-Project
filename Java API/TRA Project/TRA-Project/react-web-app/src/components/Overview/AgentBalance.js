import * as React from 'react';
import {CustomTable} from "../CustomTable";
import Title from "../Title";
import {useState, useEffect} from "react";

const axios = require('axios');

const cols = [
    { id: 'resourceId', label: 'Resource Id', minWidth: 170 },
    { id: 'resourceType', label: 'Resource Type', minWidth: 170 },
    { id: 'amount', label: 'Amount', minWidth: 170 },
];

function createData(resourceId, resourceType, amount) {
    return { resourceId, resourceType, amount };
}

export const AgentBalance = () => {
    const[rows, setRows] = useState([]);


    useEffect(async () => {
        const data = [];
        const res = await axios.get("http://localhost:8080/admin/getAllResources")
        if(res.status != 500) {
            res.data.map((resourceType) => {
                data.push(createData(resourceType.id, resourceType.name, resourceType.weight))
            })
            setRows(data);
        }
    }, [])


    return (
        <div>
            <Title>Balance</Title>
            <CustomTable columns={cols} rows={rows} showPagination={false} maxHeight={440}/>
        </div>
    )
}