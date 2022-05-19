import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import {AgentBox} from "../../components/Transfer/AgentBox";
import {ExchangeLogo} from "../../components/Transfer/ExchangeLogo";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import {ValidateTransfer} from "../../api/TransferAPI";
import React, {useState} from "react";
import Title from "../../components/Title";
import {TransformBox} from "../../components/Transform/TransformBox";
import {ConfirmTransformationModal} from "../../components/Transform/ConfirmTransformationModal";


export const DefineTransformPage = () => {
    const[inputData, setInputData] = useState([]);
    const[outputData, setOutputData] = useState([]);
    const[name, setName] = useState('');
    const[showModal, setShowModal] = useState(false);

    const handleOpen = () => {
        setShowModal(true);
    }
    const handleClose = () => setShowModal(false);

    const handleInputChange = (input) => {
        setInputData(inputData => [...inputData, input])
    }

    const handleOutputChange = (output) => {
        setOutputData(outputData => [...outputData, output])
    }

    const handleNameChange = (event) => {
        setName(event.target.value);
    }

    const handleSubmit = () => {
        if(name.length < 1) {
            alert("error when naming");
        }
        const data = {
            uuid: localStorage.getItem('user'),
            name: name,
            input: inputData,
            output: outputData
        }
        console.log("Final data:", data);
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
                                <TransformBox title={"Input"} handleInputChange={handleInputChange} type={true}/>
                            </Grid>
                            <Grid item md={8} lg={2}>
                                <ExchangeLogo />
                            </Grid>
                            <Grid item xs={12} md={8} lg={5}>
                                <TransformBox title={"Output"} handleOutputChange={handleOutputChange} type={false}/>
                            </Grid>
                        </Grid>
                        <Box textAlign='center'>
                            <div style={{paddingTop: 30}}>
                                <Button variant="contained" onClick={handleOpen}>Name transformation</Button>
                            </div>
                        </Box>
                    </form>
                </Paper>
            </Grid>
            <ConfirmTransformationModal onClose={handleClose} open={showModal} handleNameChange={handleNameChange} handleSubmit={handleSubmit} />
        </div>
    )
}