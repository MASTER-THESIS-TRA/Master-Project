import React from "react";
import {Transfer} from "../components/Transfer/Transfer";
import Grid from "@mui/material/Grid";
import {DialogContent, FormControl, InputLabel, MenuItem, MenuList, Paper, Select, TextField} from "@mui/material";
import {AgentBox} from "../components/Transfer/AgentBox";
import Title from "../components/Title";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import {CustomTable} from "../components/CustomTable";
import Divider from "@mui/material/Divider";

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


const names = [
    'Oliver Hansen',
    'Van Henry',
    'April Tucker',
    'Ralph Hubbard',
    'Omar Alexander',
    'Carlos Abbott',
    'Miriam Wagner',
    'Bradley Wilkerson',
    'Virginia Andrews',
    'Kelly Snyder',
];

const columns = [
    { id: 'name', label: 'Name', minWidth: 200 },
    { id: 'amount', label: 'Amount', minWidth: 200 },
];

function createData(name, amount) {
    return { name, amount};
}

const rows = [
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
    createData('Cheese', 4),
];

export const TransformPage = () => {

    const handleOpen = () => { return null; }

    return(
        <div>
            <Grid item xs={12} md={8} lg={5}>
                <Paper
                    elevation={2}
                    sx={{
                        p: 2,
                        display: 'flex',
                        flexDirection: 'column',
                        height: 450,
                    }}
                >
                    <Title>Transfer</Title>
                    <DialogContent dividers sx={{ overflow: 'hidden'}}>
                        <Grid container spacing={2}>
                            <Grid item xs={12} md={8} lg={6}>
                                <TextField
                                    required
                                    id="outlined-required"
                                    label="Email of reciever"
                                    sx={{ minWidth: 330, paddingBottom: '10px' }}
                                />
                                <Box>
                                    <FormControl sx={{minWidth: 200 }}>
                                        <InputLabel id="demo-simple-select-autowidth-label">Resource</InputLabel>
                                        <Select
                                            labelId="demo-simple-select-autowidth-label"
                                            id="demo-simple-select-autowidth"
                                            //value={age}
                                            //onChange={handleChange}
                                            autoWidth
                                            label="Resource"
                                            MenuProps={MenuProps}
                                        >
                                            {names.map((name) => (
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
                                    />
                                </Box>
                            </Grid>
                            <Grid item md={8} lg={6} container justifyContent="flex-end" sx={{paddingBottom: "40px"}}>
                                <CustomTable rows={rows} columns={columns} showPagination={false} />
                            </Grid>
                        </Grid>
                    </DialogContent>
                    <Box textAlign='center' sx={{paddingTop: '10px'}}>
                        <Button variant='contained' size='medium' onClick={handleOpen}>Submit</Button>
                    </Box>
                </Paper>
            </Grid>
        </div>
    )
}