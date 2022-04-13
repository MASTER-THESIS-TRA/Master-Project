import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {Paper, TextField} from "@mui/material";
import Grid from "@mui/material/Grid";
import {AgentBox} from "../transfer.tmp/AgentBox";
import {ExchangeLogo} from "../Transfer/ExchangeLogo";

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

export const ResourceModal = (props) => {
    return (
        <div>
            <Modal
                open={props.isOpen}
                onClose={props.isClosed}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box
                    component="form"
                    sx={style}
                    noValidate
                    autoComplete="off"
                >
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Add resource
                    </Typography>
                    <div>
                        <Grid container spacing={3}>
                            {/* Chart */}
                            <Grid item xs={12} md={8} lg={6}>
                                <TextField
                                    required
                                    id="outlined-required"
                                    label="Resource Name"
                                    defaultValue=" "
                                />
                            </Grid>
                            <Grid item md={8} lg={6}>
                                <TextField
                                    id="outlined-number-required"
                                    label="Amount"
                                    type="number"
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12} md={8} lg={12}>
                                <Button variant="contained" onClick={props.isClosed}>Add Resource</Button>
                            </Grid>
                        </Grid>
                    </div>
                </Box>
            </Modal>
        </div>
    );
}