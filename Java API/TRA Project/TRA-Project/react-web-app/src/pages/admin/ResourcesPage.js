import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import Title from "../../components/Title";
import {HistoryTable} from "../../components/History/HistoryTable";
import * as React from "react";
import {CustomTable} from "../../components/CustomTable";
import {useEffect, useState} from "react";
import axios from "axios";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import {CreateNewAgent} from "../../components/Agent/CreateNewAgent";
import {CreateNewResourceType} from "../../components/Resources/CreateNewResourceType";


const columns = [
    { id: 'id', label: 'Resource Id', minWidth: 170 },
    { id: 'name', label: 'Name', minWidth: 250 },
    { id: 'weight', label: 'Weight (g)', minWidth: 100 },
]

export const ResourcesPage = () => {
    const[rows, setRows] = useState([])
    const[resources, setResources] = useState({});
    const[showModal, setShowModal] = useState(false);

    const handleOpen = () => setShowModal(true);
    const handleClose = () => setShowModal(false);

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

    function createData(id, name, weight) {
        return { id, name, weight };
    }

    return(
        <div>
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column', maxHeight:700, height: 700, overflow: 'hidden' }}>
                    <Grid container spacing={2}>
                        {/* Chart */}
                        <Grid item xs={12} md={8} lg={6}>
                            <Title>List of resource types</Title>
                        </Grid>
                        <Grid item md={8} lg={6} container justifyContent="flex-end">
                            <Button variant="outlined" onClick={handleOpen}>Add new resource type</Button>
                        </Grid>
                    </Grid>
                    <CustomTable columns={columns} rows={rows} showPagination={true} maxHeight={700}/>
                </Paper>
            </Grid>
            <CreateNewResourceType open={showModal} onClose={handleClose} />
        </div>
    )
}