import * as React from 'react';
import PropTypes from 'prop-types';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Dialog from '@mui/material/Dialog';
import TextField from "@mui/material/TextField";
import {useEffect, useState} from "react";
import axios from "axios";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";

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


export const TransformationModal = (props) => {
    const { addResource, onClose, value: valueProp, open, ...other } = props;
    const [value, setValue] = React.useState(valueProp);
    const[type, setType] = useState('');
    const[amount, setAmount] = useState();
    const[options, setOptions] = useState([]);

    const handleCancel = () => {
        onClose();
    };


    const handleOk = (event) => {
        event.preventDefault();
        if((type || amount) === undefined) {
            alert("One or more fields are empty")
        } else if(parseInt(amount) < 0.001) {
            alert("Amount has a negative value!")
        } else {
            addResource(type, amount)
            onClose(value);
        }
    };

    const handleResourceTypeChange = (event) => {
        setType(event.target.value);
    };

    const handleAmountChange = (event) => {
        setAmount(event.target.value);
    };

    React.useEffect(() => {
        if (!open) {
            setValue(valueProp);
        }
    }, [valueProp, open]);

    useEffect(async () => {
        const res = await axios.get("http://localhost:8080/admin/getAllResources")
        if(res.status != 500) {
            res.data.map((type) => {
                setOptions(prevState => [...prevState, type.name])
            })
        }
    }, [])

    return (
        <Dialog
            sx={{ '& .MuiDialog-paper': { width: '100%', maxHeight: 435, minWidth: 200, textAlign: 'center'} }}
            maxWidth="xs"
            open={open}
            {...other}
        >
            <DialogTitle>Add new {props.title}</DialogTitle>
            <DialogContent dividers>
                <Box>
                    <FormControl sx={{ width: 250 }}>
                        <InputLabel id="demo-simple-select-autowidth-label">Resource</InputLabel>
                        <Select
                            labelId="demo-simple-select-autowidth-label"
                            id="demo-simple-select-autowidth"
                            onChange={handleResourceTypeChange}
                            autoWidth
                            value={type}
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
                        InputProps={{
                            inputProps: {
                                max: 999, min: 0
                            }
                        }}
                        sx={{ width: 100, paddingLeft: "5px" }}
                        onChange={e => handleAmountChange(e)}
                    />
                </Box>
            </DialogContent>
            <DialogActions>
                <Button autoFocus onClick={handleCancel}>
                    Cancel
                </Button>
                <Button onClick={e => handleOk(e)}>Add</Button>
            </DialogActions>
        </Dialog>
    );
}

TransformationModal.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired
};
