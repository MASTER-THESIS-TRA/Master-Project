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

export const TransformationModal = (props) => {
    const { addResource, onClose, value: valueProp, open, ...other } = props;
    const [value, setValue] = React.useState(valueProp);
    const[resourceType, setResourceType] = useState();
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
            sx={{ '& .MuiDialog-paper': { width: '80%', maxHeight: 435 } }}
            maxWidth="xs"
            open={open}
            {...other}
        >
            <DialogTitle>Add new {props.title}</DialogTitle>
            <DialogContent dividers>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root': { m: 1, width: '25ch' },
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <div>
                        <TextField
                            required
                            id="outlined-required"
                            label="Resource Type"
                            onChange={e => handleResourceTypeChange(e)}
                        />
                        <TextField
                            required
                            id="outlined-required"
                            label="Amount"
                            onChange={e => handleAmountChange(e)}
                        />
                    </div>
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
