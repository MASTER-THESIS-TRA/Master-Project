import React from "react";
import {ListItem, ListItemIcon, ListItemText } from "@mui/material";
import { Link as RouterLink } from 'react-router-dom';
import PropTypes from 'prop-types';
import ListItemButton from "@mui/material/ListItemButton";


export const CustomLinkButton = (props) => {
    const { icon, primary, to } = props;

    const renderLink = React.useMemo(
        () =>
            React.forwardRef(function Link(itemProps, ref) {
                return <RouterLink to={to} ref={ref} {...itemProps} role={undefined} />;
            }),
        [to],
    );

    return (
        <li>
            <ListItemButton component={renderLink}>
                <ListItemIcon>
                    {icon ? <ListItemIcon>{icon}</ListItemIcon> : null}
                </ListItemIcon>
                <ListItemText primary={primary} />
            </ListItemButton>
        </li>
    );
}

CustomLinkButton.propTypes = {
    icon: PropTypes.element,
    primary: PropTypes.string.isRequired,
    to: PropTypes.string.isRequired,
};