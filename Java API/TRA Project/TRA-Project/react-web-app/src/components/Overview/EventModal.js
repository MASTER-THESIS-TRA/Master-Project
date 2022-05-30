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

export const EventModal = (props) => {
    const { onClose, value: valueProp, open, ...other } = props;
    const [value, setValue] = React.useState(valueProp);


    return (
        <Dialog
            sx={{ '& .MuiDialog-paper': { minWidth: '600px', width: '80%', maxHeight: 435, textAlign: 'center'} }}
            maxWidth="xs"
            open={open}
            {...other}
        >
            <DialogTitle>Details of event</DialogTitle>
            <DialogContent dividers>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root': { m: 1, width: '25ch' },
                    }}
                    noValidate
                    autoComplete="off"
                >
                   <div>{props.events}</div>
                </Box>
            </DialogContent>
            <DialogActions>
                <Button autoFocus onClick={props.onClose}>
                    Close
                </Button>
            </DialogActions>
        </Dialog>
    );
}

EventModal.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired
};
