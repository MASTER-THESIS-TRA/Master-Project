import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import {AgentBox} from "../components/Transfer/AgentBox";
import {ExchangeLogo} from "../components/Transfer/ExchangeLogo";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import {ValidateTransfer} from "../api/TransferAPI";
import React, {useState} from "react";
import Title from "../components/Title";
import {TransformBox} from "../components/Transform/TransformBox";


export const DefineTransformPage = () => {
    const[transformation, setTransformation] = useState({input: [], output: []});

    const handleInputChange = (input) => {
        setTransformation(prevState => ({...prevState, input: input}))
    }

    const handleOutputChange = (output) => {
        setTransformation({...transformation, output: output})
    }

    const handleSubmit = () => {
        console.log("Transformation data:", transformation)
    }

    return (
        <div>
            <Grid item xs={12}>
                <Paper
                    elevation={2}
                    sx={{
                        p: 2,
                        display: 'flex',
                        flexDirection: 'column',
                        height: 600,
                    }}
                >
                    <Title>Define Transformation</Title>
                    <form>
                        <Grid container spacing={3}>
                            <Grid item xs={12} md={8} lg={5}>
                                <TransformBox title={"Input"} handleInputChange={handleInputChange}/>
                            </Grid>
                            <Grid item md={8} lg={2}>
                                <ExchangeLogo />
                            </Grid>
                            <Grid item xs={12} md={8} lg={5}>
                                <TransformBox title={"Output"} handleOutputChange={handleOutputChange}/>
                            </Grid>
                        </Grid>
                        <Box textAlign='center'>
                            <div style={{paddingTop: 30}}>
                                <Button variant="contained" onClick={handleSubmit}>Confirm Transformation</Button>
                            </div>
                        </Box>
                    </form>
                </Paper>
            </Grid>
        </div>
    )
}