
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import {ResourceList} from "../components/Transfer/ResourceList";
import Title from "../components/Title";
import React from "react";
import {Transfer} from "../components/Transfer/Transfer";


export const TransferPage = () => {
    return(
        <div>
            <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <Paper
                            sx={{
                                p: 2,
                                display: 'flex',
                                flexDirection: 'column',
                                height: 600
                        }}>
                            <Title>Transfer Component</Title>
                            {/* Render Transfer component */}
                            <Transfer />
                        </Paper>
                    </Grid>
                </Grid>
            </Container>
        </div>
    )
}