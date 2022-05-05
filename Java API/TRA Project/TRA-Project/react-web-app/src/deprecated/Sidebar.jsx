import * as React from 'react';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Collapse from '@mui/material/Collapse';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import {useState} from "react";
import {CustomLinkButton} from "../components/CustomLinkButton";
import {ListSubheader} from "@mui/material";


const divStyle = {
    position: 'fixed',
    width: '200px',
    height: '100%',
    background: '#2196f3',
    zIndex: '10',
};


export const Sidebar = () => {
    const [isOpen, setIsOpen] = useState(false)

    const handleClick = () => {
        setIsOpen(!isOpen)
    };

    return (
        <div style={divStyle}>
            <List component="nav">
                <CustomLinkButton to="/" primary="Overview" icon={<InboxIcon />} />
                <CustomLinkButton primary="Account" to="account" icon={<InboxIcon />} />
                <ListItemButton onClick={handleClick}>
                    <ListItemIcon>
                        <InboxIcon />
                    </ListItemIcon>
                    <ListItemText primary="Transctions" sx={{ color: '#FFFAF0' }}/>
                    {isOpen ? <ExpandLess /> : <ExpandMore />}
                </ListItemButton>
                <Collapse in={isOpen} timeout="auto" unmountOnExit>
                    <List sx={{ pl: 4 }} component="div" disablePadding>
                        <CustomLinkButton primary="Transfer" to="transfer" icon={<InboxIcon />} />
                        <CustomLinkButton primary="Transform" to="transform" icon={<InboxIcon />} />
                    </List>
                </Collapse>
            </List>
        </div>
    )
}