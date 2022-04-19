import {ResourceModal} from "../Modals/ResourceModal";
import React, {useEffect, useState} from "react";
import Button from "@mui/material/Button";
import {CustomTable} from "../CustomTable";
import List from "@mui/material/List";
import {IconButton, ListItem, ListItemAvatar, ListItemText, TableCell} from "@mui/material";
import Avatar from "@mui/material/Avatar";
import DeleteIcon from '@mui/icons-material/Delete';
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";



const columns = [
    { id: 'id', label: 'id'},
    { id: 'name', label: 'name', minWidth: "120px"},
    { id: 'amount', label: 'amount'},
];

export const ResourceList = () => {
    const[showModal, setShowModal] = useState(false);
    const[resources, setResources] = useState([])

    /*
    useEffect(() => {
        setResources(prevArray => [...prevArray, data])
    }, []); */

    const toggleModal = () => {
        setShowModal(!showModal)
    }

    const handleDelete = (id) => {
        let filteredArray = resources.filter((resource) => resource.id !== id)
        setResources(filteredArray)
    }

    const addResource = (resource) => {
        let maxId;
        if(resource.name && resource.amount != null) {
            if(resources.length < 1) {
                maxId = 1;
            } else {
                maxId = Math.max.apply(Math, resources.map(function(elem) { return elem.id; })) +1
                //maxId = (resources.find(elem => Math.max(elem.id))).id + 1;
            }
        } else {
            alert("Error adding to array");
        }
        resource = {id: maxId, name: resource.name, amount: resource.amount}
        setResources((prevState => [...prevState, resource]))
    }

    const temp = {
        name: "hello",
        amount: 1
    }

    return(
        <div>
            <Box sx={{ width: '100%', height: 350, overflow: 'hidden' }}>
                <TableContainer sx={{ maxHeight: 350 }}>
                    <Table stickyHeader aria-label="sticky table">
                        <TableHead>
                            <TableRow>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{ minWidth: column.minWidth }}
                                    >
                                        {column.label}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {resources.map((elem) =>
                                <TableRow
                                    key={elem.id}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 }}}>
                                    <TableCell component="th" scope="row">
                                        {elem.id}
                                    </TableCell>
                                    <TableCell>
                                        {elem.name}
                                    </TableCell>
                                    <TableCell>
                                        {elem.amount}
                                    </TableCell>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>

            </Box>
            <div>
                <Box margin={3} textAlign='center'>
                    <Button variant="contained" onClick={toggleModal}>Add Resource</Button>
                </Box>
            </div>

            <ResourceModal isOpen={showModal} isClosed={toggleModal} addResource={addResource}/>
        </div>
    )
}


