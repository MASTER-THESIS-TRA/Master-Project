import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import Title from "../../components/Title";
import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import Button from "@mui/material/Button";

import {LocationModal} from "../../components/Transport/LocationModal";
import {LocationTable} from "../../components/Transport/LocationTable";


const columns = [
    { id: 'id', label: 'Id', minWidth: 130 },
    { id: 'name', label: 'Name', minWidth: 150 },
    { id: 'xCoord', label: 'X-coordinate', minWidth: 150, format: (value) => value.toFixed(6),},
    { id: 'yCoord', label: 'Y-coordinate', minWidth: 150, format: (value) => value.toFixed(6),},
    { id: 'location', label: 'Location', minWidth: 100, align: "center"},
]
function createData(id, name, xCoord, yCoord ) {
    return { id, name, xCoord, yCoord};
}

const rows = [
    createData('11-22', 'Work Place', 55.6932463728027, 12.582942373108413)
];

export const AddressPage = () => {
    //const[rows, setRows] = useState([])
    const[resources, setResources] = useState({});
    const[showModal, setShowModal] = useState(false);

    const handleOpen = () => setShowModal(true);
    const handleClose = () => setShowModal(false);

    /*
    useEffect(async () => {
        const data = [];
        const res = await axios.get("http://localhost:8080/admin/getAllResources")
        if(res.status != 500) {
            res.data.map((resourceType) => {
                data.push(createData(resourceType.id, resourceType.name, resourceType.weight))
            })
            setRows(data);
        }
    }, [])
     */


    return(
        <div>
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column', maxHeight:700, height: 700, overflow: 'hidden' }}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} md={8} lg={6}>
                            <Title>List of locations</Title>
                        </Grid>
                        <Grid item md={8} lg={6} container justifyContent="flex-end">
                            <Button variant="outlined" onClick={handleOpen}>Add new location</Button>
                        </Grid>
                    </Grid>
                    <LocationTable columns={columns} rows={rows} showPagination={true} maxHeight={700}/>
                </Paper>
            </Grid>
            <LocationModal open={showModal} onClose={handleClose} />
        </div>
    )
}