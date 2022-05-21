import {CustomTable} from "../CustomTable";
import Title from "../Title";
import * as React from "react";
import {AgentBalance} from "./AgentBalance";
import {useEffect, useState} from "react";
import axios from "axios";

const columns = [
    { id: 'id', label: 'Id', minWidth: 100 },
    { id: 'eventType', label: 'Event Type', minWidth: 100 },
    { id: 'agentId', label: 'Created By', minWidth: 100 },
    { id: 'time', label: 'Time Of Creation', minWidth: 100 },
    { id: 'body', label: 'Name', minWidth: 200 },

];

export const RecentEvents = () => {
    const[rows, setRows] = useState([]);


    useEffect(async () => {
        const data = [];
        const res = await axios.get("http://localhost:8080/history/getAllEvents")
        if(res.status != 500) {
            res.data.map((elem) => {
                data.push(createData(elem.id, elem.eventType, elem.agentId, elem.time, elem.body))
            })
            setRows(data);
        }
    }, [])

    function createData(id, eventType, agentId, time, body) {
        return { id, eventType, agentId, time, body };
    }

    return (
        <div>
            <Title>Recent Events</Title>
            <CustomTable rows={rows} columns={columns} showPagination={false} maxHeight={240}/>
        </div>
    )
}