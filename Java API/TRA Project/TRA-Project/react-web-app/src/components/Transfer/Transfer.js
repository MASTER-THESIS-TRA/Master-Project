
import {ListItem, ListItemText, Paper, styled} from "@mui/material";
import React, {useState} from "react";
import Grid from "@mui/material/Grid";
import {AgentBox} from "./AgentBox";
import {ExchangeLogo} from "./ExchangeLogo";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import {ValidateTransfer} from "../../api/TransferAPI";


export const Transfer = () => {
    const[senderData, setSenderData] = useState([]);
    const[receiverData, setReceiverData] = useState([]);

    return (
        <div>
            <form>
                <Grid container spacing={3}>
                    <Grid item xs={12} md={8} lg={5}>
                        <Paper
                            elevation={6}
                            sx={{
                                p: 2,
                                display: 'flex',
                                flexDirection: 'column',
                                height: 450,
                            }}
                        >
                            <AgentBox setFormData={setSenderData} />
                        </Paper>
                    </Grid>
                    <Grid item md={8} lg={2}>
                        <ExchangeLogo />
                    </Grid>
                    <Grid item xs={12} md={8} lg={5}>
                        <Paper
                            elevation={6}
                            sx={{
                                p: 2,
                                display: 'flex',
                                flexDirection: 'column',
                                height: 450,
                            }}
                        >
                            <AgentBox setFormData={setReceiverData} />
                        </Paper>
                    </Grid>
                </Grid>
                <Box textAlign='center'>
                    <div style={{paddingTop: 30}}>
                        <Button variant="contained" onClick={() => ValidateTransfer(senderData, receiverData)}>Show parent state</Button>
                    </div>
                </Box>
            </form>
        </div>
    )

}