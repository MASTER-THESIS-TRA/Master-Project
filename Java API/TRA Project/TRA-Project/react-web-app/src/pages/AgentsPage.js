import Grid from "@mui/material/Grid";
import {Paper, TextField} from "@mui/material";
import Title from "../components/Title";
import {HistoryTable} from "../components/History/HistoryTable";
import * as React from "react";
import {CustomTable} from "../components/CustomTable";
import {useEffect, useState} from "react";
import axios from "axios";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import Typography from "@mui/material/Typography";
import {CreateNewAgent} from "../components/Agent/CreateNewAgent";


const columns = [
    { id: 'name', label: 'Name', minWidth: 170 },
    { id: 'code', label: 'ISO\u00a0Code', minWidth: 100 },
    {
        id: 'population',
        label: 'Population',
        minWidth: 170,
        align: 'right',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'size',
        label: 'Size\u00a0(km\u00b2)',
        minWidth: 170,
        align: 'right',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'density',
        label: 'Density',
        minWidth: 170,
        align: 'right',
        format: (value) => value.toFixed(2),
    },
];

function createData(name, code, population, size) {
    const density = population / size;
    return { name, code, population, size, density };
}

const rows = [
    createData('India', 'IN', 1324171354, 3287263),
    createData('China', 'CN', 1403500365, 9596961),
    createData('Italy', 'IT', 60483973, 301340),
    createData('United States', 'US', 327167434, 9833520),
    createData('Canada', 'CA', 37602103, 9984670),
    createData('Australia', 'AU', 25475400, 7692024),
    createData('Germany', 'DE', 83019200, 357578),
    createData('Ireland', 'IE', 4857000, 70273),
    createData('Mexico', 'MX', 126577691, 1972550),
    createData('Japan', 'JP', 126317000, 377973),
    createData('France', 'FR', 67022000, 640679),
    createData('United Kingdom', 'GB', 67545757, 242495),
    createData('Russia', 'RU', 146793744, 17098246),
    createData('Nigeria', 'NG', 200962417, 923768),
    createData('Brazil', 'BR', 210147125, 8515767),
];


const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export const AgentPage = () => {
    const[agents, setAgents] = useState({});
    const[showModal, setShowModal] = useState(false);

    const handleOpen = () => setShowModal(true);
    const handleClose = () => setShowModal(false);
    /*
    useEffect(() => {
        //fetchAgents()
    }, [])

    const fetchAgents = () => {
        axios.get("http://localhost:8080/user/getUserInfo")
            .then((response) => {
                    setAgents(response.data)
                    console.log(response.data.json())
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }

     */




    return(
        <div>
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column', maxHeight:700, height: 700, overflow: 'hidden' }}>
                    <Grid container spacing={2}>
                        {/* Chart */}
                        <Grid item xs={12} md={8} lg={6}>
                            <Title>List of Agents</Title>
                        </Grid>
                        <Grid item md={8} lg={6} container justifyContent="flex-end">
                            <Button variant="outlined" onClick={handleOpen}>Add new agent</Button>
                        </Grid>
                    </Grid>
                    <CustomTable columns={columns} rows={rows} showPagination={true} maxHeight={700}/>
                </Paper>
            </Grid>
            <Modal
                open={showModal}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <CreateNewAgent open={showModal} onClose={handleClose} />
            </Modal>
        </div>
    )
}

