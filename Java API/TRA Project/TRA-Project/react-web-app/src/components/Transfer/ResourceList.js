import Box from "@mui/material/Box";
import List from "@mui/material/List";
import {ListItem, ListItemText, styled} from "@mui/material";
import React, {useState} from "react";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import { FixedSizeList } from 'react-window';
import {AddButton} from "../AddButton";
import {ResourceModal} from "../Modals/ResourceModal";


export class ResourceList extends React.Component {
    state = {
        resources: [],
        showModal: false,
    }

    toggleModal = () => {
        this.setState({
            showModal: !this.state.showModal })
    }

    setResources = (resource) => {
        this.setState({resources: resource})
    }



    render() {
        return (
            <div>
                <button onClick={this.toggleModal}>Show Modal</button>
                <ResourceModal isOpen={this.state.showModal} isClosed={this.toggleModal}/>
            </div>
        )
    }
}

