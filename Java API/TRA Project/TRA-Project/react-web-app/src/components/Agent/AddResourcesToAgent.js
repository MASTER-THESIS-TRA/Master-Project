import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";
import PropTypes from "prop-types";
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

export const AddResourcesToAgent = (props) => {
    const {onClose, value: valueProp, open, ...other} = props;
    const [value, setValue] = React.useState(valueProp);
    const [agentEmail, setAgentEmail] = useState('');
    const [resourceType, setResourceType] = useState('');
    const [amount, setAmount] = useState();
    const[options, setOptions] = useState([])

    const handleCancel = () => {
        onClose();
    };

    function createData(agentEmail, resourceType, amount) {
        return {agentEmail, resourceType, amount};
    }

    const handleOk = () => {
        addResource(agentEmail, resourceType, amount)
        onClose(value);
        //window.location.reload();
    };

    const handleAgentEmailChange = (event) => {
        setAgentEmail(event.target.value);
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

    useEffect(async () => {
        const res = await axios.get("http://localhost:8080/admin/getAllResources")
        if(res.status != 500) {
            res.data.map((resourceType) => {
                setOptions(prevState => [...prevState, resourceType.name])
            })
        }
    }, [])
    const addResource = (email, resource, amount) => {
        const data = {
            email: email,
            resourceType: resource,
            amount: amount
        }


        axios.post("http://localhost:8080/admin/giveResource", data, {
            headers: {
                'Accept': 'application/json',
                'content-type': 'application/json'
            }})
            .then((response) => {
                    console.log(response)
                    alert(response.data)
                    window.location.reload();
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }

    return (
        <Dialog
            sx={{'& .MuiDialog-paper': {width: '100%', maxHeight: 435, minWidth: 200, textAlign: 'center'}}}
            maxWidth="xs"
            open={open}
            {...other}
        >
            <DialogTitle>Add resource to agent</DialogTitle>
            <DialogContent dividers>
                <Box>
                    <Box sx={{paddingBottom: "5px"}}>
                        <TextField
                            required
                            id="outlined-required"
                            label="Agent email"
                            sx={{width: 350}}
                            onChange={e => handleAgentEmailChange(e)}
                        />
                    </Box>
                    <Box>
                        <FormControl sx={{width: 250}}>
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
                            sx={{width: 100, paddingLeft: "5px"}}
                            onChange={e => handleAmountChange(e)}
                        />
                    </Box>
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

AddResourcesToAgent.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired
};
