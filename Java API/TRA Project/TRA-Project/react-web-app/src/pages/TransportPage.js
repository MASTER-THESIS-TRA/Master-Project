import React, {useEffect, useState} from "react";
import {Transfer} from "../components/Transfer/Transfer";
import Grid from "@mui/material/Grid";
import {DialogContent, FormControl, InputLabel, MenuItem, MenuList, Paper, Select, TextField} from "@mui/material";
import {AgentBox} from "../components/Transfer/AgentBox";
import Title from "../components/Title";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import {CustomTable} from "../components/CustomTable";
import Divider from "@mui/material/Divider";
import axios from "axios";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
    PaperProps: {
        style: {
            maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
            width: 250,
        },
    },
};

export const TransportPage = () => {
    const[receiver, setReceiver] = useState('');
    const[resourceName, setResourceName] = useState('');
    const[amount, setAmount] = useState();
    const[options, setOptions] = useState([])

    const handleReceiverChange = (event) => { setReceiver(event.target.value); };
    const handleResourceNameChange = (event) => { setResourceName(event.target.value); };
    const handleAmountChange = (event) => { setAmount(event.target.value); };

    useEffect(async () => {
        const res = await axios.get("http://localhost:8080/admin/getAllResources")
        if(res.status != 500) {
            res.data.map((resourceType) => {
                setOptions(prevState => [...prevState, resourceType.name])
            })
        }
    }, [])

    const handleSubmit = (event) => {
        event.preventDefault();
        if((receiver, resourceName, amount) === undefined) {
            alert("One or more fields are empty")
        }
        createNewTransfer(receiver, resourceName, amount);
    }

    const createNewTransfer = (receiver, resourceName, amount) => {
        const data = {
            sender: localStorage.getItem('user'),
            receiver: receiver,
            resourceName: resourceName,
            amount: amount
        }

        axios.post("http://localhost:8080/transfer/createTransfer", data, {
            headers: {
                'Accept': 'application/json',
                'content-type': 'application/json'
            }})
            .then((response) => {
                    console.log("API Response", response.data)
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }


    return(
        <div>
            <Grid container
                  spacing={0}
                  direction="column"
                  alignItems="center"
                  justifyContent="center"
            >
                <Paper
                    elevation={2}
                    sx={{
                        p: 2,
                        display: 'flex',
                        flexDirection: 'column',
                        height: 400,
                        width: 450,
                        textAlign: 'center'
                    }}
                >
                    <Title>Transport</Title>
                    <DialogContent dividers sx={{ overflow: 'hidden'}}>
                        <TextField
                            required
                            id="outlined-required"
                            label="Name"
                            sx={{ width: "90%", paddingBottom: "10px"}}
                            onChange={e => handleAmountChange(e)}
                        />
                        <FormControl sx={{width: "90%", paddingBottom: "10px" }}>
                            <InputLabel id="demo-simple-select-autowidth-label">From</InputLabel>
                            <Select
                                labelId="demo-simple-select-autowidth-label"
                                id="demo-simple-select-autowidth"
                                onChange={handleResourceNameChange}
                                autoWidth
                                value={resourceName}
                                label="Resource"
                                MenuProps={MenuProps}
                            >
                                {options.map((name) => (
                                    <MenuItem
                                        key={name}
                                        value={name}
                                    >
                                        {name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>

                        <FormControl sx={{width: "90%", paddingBottom: "10px" }}>
                            <InputLabel id="demo-simple-select-autowidth-label">To</InputLabel>
                            <Select
                                labelId="demo-simple-select-autowidth-label"
                                id="demo-simple-select-autowidth"
                                onChange={handleResourceNameChange}
                                autoWidth
                                value={resourceName}
                                label="Resource"
                                MenuProps={MenuProps}
                            >
                                {options.map((name) => (
                                    <MenuItem
                                        key={name}
                                        value={name}
                                    >
                                        {name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                        <Box>
                            <FormControl sx={{minWidth: 200 }}>
                                <InputLabel id="demo-simple-select-autowidth-label">Resource</InputLabel>
                                <Select
                                    labelId="demo-simple-select-autowidth-label"
                                    id="demo-simple-select-autowidth"
                                    onChange={handleResourceNameChange}
                                    autoWidth
                                    value={resourceName}
                                    label="Resource"
                                    MenuProps={MenuProps}
                                >
                                    {options.map((name) => (
                                        <MenuItem
                                            key={name}
                                            value={name}
                                        >
                                            {name}
                                        </MenuItem>
                                    ))}
                                </Select>
                            </FormControl>
                            <TextField
                                required
                                id="outlined-required"
                                label="Amount"
                                type="number"
                                sx={{ width: 130, paddingLeft: "5px" }}
                                onChange={e => handleAmountChange(e)}
                            />
                        </Box>
                    </DialogContent>
                    <Box textAlign='center' sx={{paddingTop: '10px'}}>
                        <Button variant='contained' size='medium' onClick={handleSubmit}>Submit</Button>
                    </Box>
                </Paper>
            </Grid>
        </div>
    )
}