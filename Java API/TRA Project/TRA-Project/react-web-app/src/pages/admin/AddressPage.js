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
    { id: 'longitude', label: 'Longitude', minWidth: 150, format: (value) => value.toFixed(6),},
    { id: 'latitude', label: 'Latitude', minWidth: 150, format: (value) => value.toFixed(6),},
    { id: 'location', label: 'Location', minWidth: 100, align: "center"},
]



export const AddressPage = () => {
    //const[rows, setRows] = useState([])
    const[rows, setRows] = useState([]);
    const[showModal, setShowModal] = useState(false);

    const handleOpen = () => setShowModal(true);
    const handleClose = () => setShowModal(false);


    useEffect(async () => {
        const data = [];
        const res = await axios.get("http://localhost:8080/admin/getLocations")
        console.log("RES:", res);
        if(res.status != 500) {
            res.data.map((location) => {
                data.push(createData(location.id, location.name, location.longitude, location.latitude))
            })
            setRows(data);
        }
    }, [])

    function createData(id, name, longitude, latitude ) {
        return { id, name, longitude, latitude};
    }

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