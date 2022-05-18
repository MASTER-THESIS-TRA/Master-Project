import * as React from 'react';
import ListSubheader from '@mui/material/ListSubheader';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import PeopleIcon from '@mui/icons-material/People';
import BarChartIcon from '@mui/icons-material/BarChart';
import AssignmentIcon from '@mui/icons-material/Assignment';
import {CustomLinkButton} from "./CustomLinkButton";

export const mainListItems = (
    <React.Fragment>
        <CustomLinkButton primary="Overview" to="/overview" icon={<DashboardIcon />} />
        <CustomLinkButton primary="Transfer" to="transfer" icon={<ShoppingCartIcon />} />
        <CustomLinkButton primary="Transform" to="transform" icon={<PeopleIcon />} />
        <CustomLinkButton primary="History" to="history" icon={<BarChartIcon />} />
    </React.Fragment>
);

export const secondaryListItems = (
    <React.Fragment>
        <ListSubheader component="div" inset>
            Administrator
        </ListSubheader>
        <CustomLinkButton primary="Agents" to="agents" icon={<AssignmentIcon />} />
        <CustomLinkButton primary="Resources" to="resources" icon={<AssignmentIcon />} />
        <CustomLinkButton primary="Transformations" to="account" icon={<AssignmentIcon />} />
    </React.Fragment>
);