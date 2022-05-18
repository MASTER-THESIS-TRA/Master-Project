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

export const CreateNewAgent = (props) => {
    const { onClose, value: valueProp, open, ...other } = props;
    const [value, setValue] = React.useState(valueProp);
    const[aName, setName] = useState();
    const[aEmail, setEmail] = useState();
    const[aPassword, setPassword] = useState();

    const handleCancel = () => {
        onClose();
    };

    const handleOk = (event) => {
        createNewAgentData(aName, aEmail, aPassword, event);
        onClose(value);
    };

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };
    React.useEffect(() => {
        if (!open) {
            setValue(valueProp);
        }
    }, [valueProp, open]);

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

    return (
        <Dialog
            sx={{ '& .MuiDialog-paper': { width: '80%', maxHeight: 435 } }}
            maxWidth="xs"
            open={open}
            {...other}
        >
            <DialogTitle>Add new agent</DialogTitle>
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
                            label="Name"
                            onChange={e => handleNameChange(e)}
                        />
                        <TextField
                            required
                            id="outlined-required"
                            label="Email"
                            onChange={e => handleEmailChange(e)}
                        />
                        <TextField
                            required
                            id="outlined-required"
                            label="Password"
                            onChange={e => handlePasswordChange(e)}
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

CreateNewAgent.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired
};
