import * as React from "react";
import {useState} from "react";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";
import PropTypes from "prop-types";


export const ConfirmTransformationModal = (props) => {
    const { onClose, value: valueProp, open, ...other } = props;
    const [value, setValue] = React.useState(valueProp);
    const[name, setName] = useState();

    const handleCancel = () => {
        onClose();
    };

    const handleNameChange = (event) => {
        props.handleNameChange(event);
    }


    const handleOk = () => {
        props.handleSubmit();
        onClose();
    };


    React.useEffect(() => {
        if (!open) {
            setValue(valueProp);
        }
    }, [valueProp, open]);


    return (
        <Dialog
            sx={{ '& .MuiDialog-paper': { width: '80%', maxHeight: 435 } }}
            maxWidth="xs"
            open={open}
            {...other}
        >
            <DialogTitle>Name Transformation</DialogTitle>
            <DialogContent dividers>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root': { m: 1 },
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        required
                        id="outlined-required"
                        label="Name of transformation"
                        sx={{ width: '100%' }}
                        onChange={e => handleNameChange(e)}
                    />
                </Box>
            </DialogContent>
            <DialogActions>
                <Button autoFocus onClick={handleCancel}>
                    Cancel
                </Button>
                <Button onClick={e => handleOk(e)}>Complete</Button>
            </DialogActions>
        </Dialog>
    );
}

ConfirmTransformationModal.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired
};