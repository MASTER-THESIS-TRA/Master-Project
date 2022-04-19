import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {FormControl, Paper, TextField} from "@mui/material";
import Grid from "@mui/material/Grid";
import {useCallback, useState} from "react";

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 450,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

const initialState = {
    name: null,
    amount: NaN
}

export const ResourceModal = (props) => {
    const[data, setData] = useState(initialState)

    const onSubmit = (event) => {
        event.preventDefault();
        props.addResource(data);
        props.isClosed();
        setData(initialState);
    }

    const onNameChange = (e) => {
        setData((prevState) => ({ ...prevState, name: e.target.value, amount: data.amount}));
    }

    const onAmountChange = (e) => {
        setData((prevState) => ({ ...prevState, name: data.name, amount: parseInt(e.target.value)}));
    }


    return (
        <div>
            <Modal
                open={props.isOpen}
                onClose={props.isClosed}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box
                    sx={style}
                >
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Add resource
                    </Typography>
                    <form onSubmit={onSubmit}>
                        <div>
                            <Grid container spacing={3}>
                                {/* Chart */}
                                <Grid item xs={12} md={8} lg={6}>
                                    <TextField
                                        required={true}
                                        id="outlined-required"
                                        label="Resource Name"
                                        onChange={(e) => onNameChange(e)}
                                    />
                                </Grid>
                                <Grid item md={8} lg={6}>
                                    <TextField
                                        required={true}
                                        id="outlined-number-required"
                                        label="Amount"
                                        type="number"
                                        onChange={(e) => onAmountChange(e)}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                    />
                                </Grid>
                                <Grid item xs={12} md={8} lg={12}>
                                    <Button variant="contained" type="submit">Add Resource</Button>
                                </Grid>
                            </Grid>
                        </div>
                    </form>
                </Box>
            </Modal>
        </div>
    );
}