import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import {HistoryTable} from "../components/History/HistoryTable";
import Title from "../components/Title";
import * as React from "react";

export const HistoryPage = () => {
    return(
        <div>
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column', maxHeight:700, height: 700, overflow: 'hidden' }}>
                    <Title>History of events</Title>
                    <HistoryTable />
                </Paper>
            </Grid>
        </div>

    )
}