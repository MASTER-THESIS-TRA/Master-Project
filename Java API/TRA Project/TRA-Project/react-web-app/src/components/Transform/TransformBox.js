import {DialogContent, Paper} from "@mui/material";
import Title from "../Title";
import React from "react";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import {TransformationModal} from "./TransformationModal";
import {useState} from "react";
import {CustomTable} from "../CustomTable";
import {useEffect, useRef} from "react";

const columns = [
    { id: 'resourceType', label: 'Resource Type', minWidth: 170,  fontWeight: 'bold' },
    { id: 'amount', label: 'Amount', minWidth: 100, align: 'right'},
];

function createData(resourceType, amount) {
    return { resourceType, amount };
}


export const TransformBox = (props) => {
    const[showModal, setShowModal] = useState(false);
    const[rows, setRows] = useState([]);

    const handleOpen = () => setShowModal(true);
    const handleClose = () => setShowModal(false);

    const addResource = (resourceType, amount) => {
        if(props.type) {
            props.handleInputChange(createData(resourceType, amount));
        }
        if(!props.type) {
            props.handleOutputChange(createData(resourceType, amount))
        }
        setRows(prevState => [...prevState, createData(resourceType, amount)])
    }

    return(
        <div>
            <Paper
                elevation={4}
                sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column',
                    height: 450,
                }}
            >
                <Grid container spacing={2} sx={{ paddingBottom: 1}}>
                    {/* Chart */}
                    <Grid item xs={12} md={8} lg={6}>
                        <Title>{props.title}</Title>
                    </Grid>
                    <Grid item md={8} lg={6} container justifyContent="flex-end">
                        <Button variant="outlined" size="small" onClick={handleOpen}>Add new {props.title}</Button>
                    </Grid>
                </Grid>
                <DialogContent dividers sx={{ overflow: 'hidden'}}>
                    <CustomTable rows={rows} columns={columns} maxHeight={350}/>
                </DialogContent>
            </Paper>
            <TransformationModal onClose={handleClose} open={showModal} title={props.title.toLowerCase()} addResource={addResource}/>
        </div>
    )
}