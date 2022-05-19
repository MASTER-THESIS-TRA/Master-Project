import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import Title from "../components/Title";
import * as React from "react";
import {CustomTable} from "../components/CustomTable";
import {useEffect, useState} from "react";
import axios from "axios";
import Button from "@mui/material/Button";
import {CreateNewAgent} from "../components/Agent/CreateNewAgent";
import {AddResourcesToAgent} from "../components/Agent/AddResourcesToAgent";
import Box from "@mui/material/Box";


const columns = [
    { id: 'uuid', label: 'UUID', minWidth: 170 },
    { id: 'name', label: 'Name', minWidth: 100 },
    { id: 'email', label: 'Email', minWidth: 100 },
    { id: 'password', label: 'Password', minWidth: 100 },
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
    const[row, setRow] = useState([]);
    const[showNewAgentModal, setShowNewAgentModal] = useState(false);
    const[showResourceModal, setShowResourceModal] = useState(false);

    const handleAgentOpen = () => setShowNewAgentModal(true);
    const handleAgentClose = () => setShowNewAgentModal(false);

    const handleResourceOpen = () => setShowResourceModal(true);
    const handleResourceClose = () => setShowResourceModal(false);

    useEffect(async () => {
        const data = []
        const res = await axios.get("http://localhost:8080/admin/allAgents")
        if(res.status != 500) {
            res.data.map((agent) => {
                data.push(createData(agent.uuid, agent.name, agent.email, agent.password))
            })
            setRow(data);
        }
    }, [])

    const createData = (uuid, name, email, password) => {
        return { uuid, name, email, password };
    }

    return(
        <div>
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column', maxHeight:700, height: 700, overflow: 'hidden' }}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} md={8} lg={6}>
                            <Title>List of Agents</Title>
                        </Grid>
                        <Grid item md={8} lg={6} container justifyContent="flex-end">
                            <Box sx={{paddingRight: "5px"}}>
                                <Button variant="outlined" onClick={handleAgentOpen}>Add new agent</Button>
                            </Box>
                            <Box>
                                <Button variant="outlined" color="success" onClick={handleResourceOpen}>Add resources to agent</Button>
                            </Box>
                        </Grid>
                    </Grid>
                    <CustomTable columns={columns} rows={row} showPagination={true} maxHeight={700}/>
                </Paper>
            </Grid>
            <CreateNewAgent open={showNewAgentModal} onClose={handleAgentClose} />
            <AddResourcesToAgent onClose={handleResourceClose} open={showResourceModal} />
        </div>
    )
}

