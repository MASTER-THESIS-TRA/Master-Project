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

export const TransferPage = () => {
    const[receiver, setReceiver] = useState('');
    const[resource, setResource] = useState();
    const[amount, setAmount] = useState();
    const[options, setOptions] = useState([])

    const handleReceiverChange = (event) => { setReceiver(event.target.value); };
    const handleResourceNameChange = (event) => { setResource(event.target.value); };
    const handleAmountChange = (event) => { setAmount(event.target.value); };

    useEffect(async () => {
        const id = localStorage.getItem('user');
        const res = await axios.get(`http://localhost:8080/overview/getBalance/${id}`)
        console.log("res:", res)
        if(res.status != 500) {
            res.data.map((resourceType) => {
                setOptions(prevState => ([...prevState, resourceType]))
            })
        }
    }, [])


    const handleSubmit = (event) => {
        event.preventDefault();
        if((receiver || resource || amount) === undefined) {
            alert("One or more fields are empty")
        } else if(parseInt(amount) < 0.001) {
            alert("Amount has a negative value!")
        } else {
            createNewTransfer(receiver, resource, amount);
        }
    }

    const createNewTransfer = (receiver, resource, amount) => {
        const data = {
            sender: localStorage.getItem('user'),
            receiver: receiver,
            resourceName: resource.type,
            amount: amount
        }

        axios.post("http://localhost:8080/transfer/createTransfer", data, {
            headers: {
                'Accept': 'application/json',
                'content-type': 'application/json'
            }})
            .then((response) => {
                    alert(response.data)
                    window.location.reload();
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
                        width: 500,
                        textAlign: 'center'
                    }}
                >
                    <Title>Transfer</Title>
                    <DialogContent dividers sx={{ overflow: 'hidden'}}>
                        <TextField
                            required
                            id="outlined-required"
                            label="Email of reciever"
                            sx={{ minWidth: 400, paddingBottom: '10px' }}
                            onChange={e => handleReceiverChange(e)}
                        />
                        <Box sx={{minWidth: 400 }}>
                            <FormControl sx={{width: 270 }}>
                                <InputLabel id="demo-simple-select-autowidth-label">Resource</InputLabel>
                                <Select
                                    labelId="demo-simple-select-autowidth-label"
                                    id="demo-simple-select-autowidth"
                                    onChange={handleResourceNameChange}
                                    autoWidth
                                    value={resource}
                                    label="Resource"
                                    MenuProps={MenuProps}
                                >
                                    {options.map((name) => (
                                        <MenuItem
                                            key={name}
                                            value={name}
                                        >
                                            {name.type}
                                        </MenuItem>
                                    ))}
                                </Select>
                            </FormControl>
                            <TextField
                                required
                                id="outlined-required"
                                label="Amount"
                                type="number"
                                InputProps={{
                                    inputProps: {
                                        max: 999, min: 0
                                    }
                                }}
                                sx={{ width: 130, paddingLeft: "5px" }}
                                onChange={e => handleAmountChange(e)}
                            />
                        </Box>
                        <Box
                            component="form"
                            sx={{'& .MuiTextField-root': { m: 1, width: 400, paddingTop: "15px" },}}
                            noValidate
                            autoComplete="off"
                        >
                            <TextField
                                id="outlined-read-only-input"
                                disabled
                                multiline
                                helperText="Details of resource"
                                value={resource != undefined ? `Name: ${resource.type}\nOwned: ${resource.amount} (g)`: null}
                                rows={2}
                            />
                        </Box>
                    </DialogContent>
                    <Box textAlign='center' sx={{paddingTop: '10px'}}>
                        <Button variant='contained' href={"localhost:3000/"} size='medium' onClick={handleSubmit}>Send</Button>
                    </Box>
                </Paper>
            </Grid>
        </div>
    )
}