import * as React from 'react';
import ListSubheader from '@mui/material/ListSubheader';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import PeopleIcon from '@mui/icons-material/People';
import BarChartIcon from '@mui/icons-material/BarChart';
import AssignmentIcon from '@mui/icons-material/Assignment';
import TransformIcon from '@mui/icons-material/Transform';
import HistoryToggleOffIcon from '@mui/icons-material/HistoryToggleOff';
import PersonAddAltIcon from '@mui/icons-material/PersonAddAlt';
import NoteAddIcon from '@mui/icons-material/NoteAdd';
import CompareArrowsIcon from '@mui/icons-material/CompareArrows';
import ConnectingAirportsIcon from '@mui/icons-material/ConnectingAirports';
import {CustomLinkButton} from "./CustomLinkButton";

export const mainListItems = (
    <React.Fragment>
        <CustomLinkButton primary="Overview" to="/overview" icon={<DashboardIcon />} />
        <CustomLinkButton primary="Transfer" to="transfer" icon={<CompareArrowsIcon />} />
        <CustomLinkButton primary="Transform" to="transform" icon={<TransformIcon />} />
        <CustomLinkButton primary="Transport" to="transport" icon={<ConnectingAirportsIcon /> } />
        <CustomLinkButton primary="History" to="history" icon={<HistoryToggleOffIcon />} />
    </React.Fragment>
);

export const secondaryListItems = (
    <React.Fragment>
        <ListSubheader component="div" inset>
            Administrator
        </ListSubheader>
        <CustomLinkButton primary="Agents" to="agents" icon={<PersonAddAltIcon />} />
        <CustomLinkButton primary="Resources" to="resources" icon={<AssignmentIcon />} />
        <CustomLinkButton primary="Transformations" to="transformations" icon={<NoteAddIcon />} />
    </React.Fragment>
);