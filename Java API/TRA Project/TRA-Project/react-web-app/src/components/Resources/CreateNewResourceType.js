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
import {InputAdornment} from "@mui/material";

export const CreateNewResourceType = (props) => {
    const { onClose, value: valueProp, open, ...other } = props;
    const [value, setValue] = React.useState(valueProp);
    const[name, setName] = useState();
    const[weight, setWeight] = useState();

    const handleCancel = () => {
        onClose();
    };

    const handleOk = (event) => {
        createNewAgentData(name, weight, event);
        onClose(value);
    };

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    const handleWeightChange = (event) => {
        setWeight(event.target.value);
    };

    React.useEffect(() => {
        if (!open) {
            setValue(valueProp);
        }
    }, [valueProp, open]);

    const createNewAgentData = (name, weight, event) => {
        event.preventDefault();

        const data = {
            name: name,
            weight: weight,
        }
        axios.post("http://localhost:8080/admin/createResourceType", data, {
            headers: {
                'Accept': 'application/json',
                'content-type': 'application/json'
            }})
            .then((response) => {
                    window.location.reload();
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
            <DialogTitle>Add resource type</DialogTitle>
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
                            label="Weight"
                            onChange={e => handleWeightChange(e)}
                            helperText="Weight of product in kg"
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

CreateNewResourceType.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired
};
