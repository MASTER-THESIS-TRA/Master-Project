import React from "react";
import {ListItem, ListItemIcon, ListItemText } from "@mui/material";
import { Link as RouterLink } from 'react-router-dom';
import PropTypes from 'prop-types';


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
            <ListItem button component={renderLink}>
                {icon ? <ListItemIcon>{icon}</ListItemIcon> : null}
                <ListItemText primary={primary} sx={{ color: '#FFFAF0' }}/>
            </ListItem>
        </li>
    );
}

CustomLinkButton.propTypes = {
    icon: PropTypes.element,
    primary: PropTypes.string.isRequired,
    to: PropTypes.string.isRequired,
};