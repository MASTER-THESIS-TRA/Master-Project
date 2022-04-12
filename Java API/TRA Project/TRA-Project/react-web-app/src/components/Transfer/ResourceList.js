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
        showModal: false,
    }

    toggleModal = () => {
        this.setState({ showModal: !this.state.showModal })
    }

    render() {
        return (
            <div>
                <CustomButton test={this.toggleModal}></CustomButton>
                <ResourceModal isOpen={this.state.showModal} isClosed={this.toggleModal} />
            </div>
        )
    }
}

