import * as React from 'react';
import PropTypes from 'prop-types';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Dialog from '@mui/material/Dialog';
import TextField from "@mui/material/TextField";
import {useState} from "react";
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


export const TransformationModal = (props) => {
    const { addResource, onClose, value: valueProp, open, ...other } = props;
    const [value, setValue] = React.useState(valueProp);
    const[resourceType, setResourceType] = useState('');
    const[amount, setAmount] = useState();

    const handleCancel = () => {
        onClose();
    };

    function createData(resourceType, amount) {
        return { resourceType, amount };
    }

    const handleOk = (event) => {
        addResource(resourceType, amount)
        onClose(value);
    };

    const handleResourceTypeChange = (event) => {
        setResourceType(event.target.value);
    };

    const handleAmountChange = (event) => {
        setAmount(event.target.value);
    };

    React.useEffect(() => {
        if (!open) {
            setValue(valueProp);
        }
    }, [valueProp, open]);

    /*
    const createNewAgentData = (name, email, password, event) => {
        event.preventDefault();

        const data = {
            name: name,
            email: email,
            password: password
        }
        console.log(JSON.stringify(data))
        axios.post("http://localhost:8080/agent/newAgent", data, {
            headers: {
                'Accept': 'application/json',
                'content-type': 'application/json'
            }})
            .then((response) => {
                    console.log("API Response", response)
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }

     */

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
                            value={resourceType}
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
