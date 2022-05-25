import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import Title from "../components/Title";
import * as React from "react";
import {CustomTable} from "../components/CustomTable";
import axios from "axios";
import {useState, useEffect} from "react";

const columns = [
    { id: 'id', label: 'Id', minWidth: 100 },
    { id: 'eventType', label: 'Event Type', minWidth: 130 },
    { id: 'agentId', label: 'Created By', minWidth: 100 },
    { id: 'time', label: 'Time Of Creation', minWidth: 100 },
    { id: 'body', label: 'Name', minWidth: 200 },

];


export const HistoryPage = () => {
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

    return(
        <div>
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column', maxHeight:700, height: 700, overflow: 'hidden' }}>
                    <Title>History of events</Title>
                    <CustomTable rows={rows} columns={columns} showPagination={true} />
                </Paper>
            </Grid>
        </div>

    )
}