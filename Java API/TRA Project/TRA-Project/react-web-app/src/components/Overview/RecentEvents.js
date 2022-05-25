import Title from "../Title";
import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import {EventTable} from "./EventTable";

const columns = [
    { id: 'id', label: 'Id', minWidth: 100 },
    { id: 'eventType', label: 'Event Type', minWidth: 130 },
    { id: 'agentId', label: 'Created By', minWidth: 100 },
    { id: 'time', label: 'Time Of Creation', minWidth: 100 },
    { id: 'body', label: 'Details', minWidth: 200 },

];

export const RecentEvents = () => {
    const[rows, setRows] = useState([]);

    useEffect(async () => {
        const id = localStorage.getItem('user');
        const data = [];
        const res = await axios.get(`http://localhost:8080/history/getEventsById/${id}`)
        if(res.status != 500) {
            console.log(res.data)
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
            <EventTable rows={rows} columns={columns} showPagination={false} maxHeight={240}/>
        </div>
    )
}