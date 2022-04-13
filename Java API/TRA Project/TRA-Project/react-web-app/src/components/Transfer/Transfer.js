
import {ListItem, ListItemText, Paper, styled} from "@mui/material";
import React, {useState} from "react";
import Grid from "@mui/material/Grid";
import {AgentBox} from "./AgentBox";
import {ExchangeLogo} from "./ExchangeLogo";


export class Transfer extends React.Component {


    render() {
        return (
            <div>
                <Grid container spacing={3}>
                    {/* Chart */}
                    <Grid item xs={12} md={8} lg={5}>
                        <Paper
                            sx={{
                                p: 2,
                                display: 'flex',
                                flexDirection: 'column',
                                height: 240,
                            }}
                        >
                            <AgentBox />
                        </Paper>
                    </Grid>
                    <Grid item md={8} lg={2}>
                        <ExchangeLogo />
                    </Grid>
                    <Grid item xs={12} md={8} lg={5}>
                        <Paper
                            sx={{
                                p: 2,
                                display: 'flex',
                                flexDirection: 'column',
                                height: 240,
                            }}
                        >
                            <AgentBox />
                        </Paper>
                    </Grid>
                </Grid>
            </div>
        )
    }
}