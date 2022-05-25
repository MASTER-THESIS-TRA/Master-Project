import Title from "../Title";
import Typography from "@mui/material/Typography";
import {useEffect, useState} from "react";
import axios from "axios";

export const AccountList = () => {
    const[userData, setUserData] = useState({});

    useEffect(() => {
        let id = localStorage.getItem('user');
        axios.get(`http://localhost:8080/overview/getAgent/${id}`)
            .then((response) => {
                    setUserData(response.data)
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }, [])

    return (
        <div>
            <Title>Account info</Title>
            <Typography component="p" variant="h4">
                Hello {userData.name}!
            </Typography>
            <p></p>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Account ID: {localStorage.getItem('user')}
            </Typography>
            <p></p>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Email: {userData.email}
            </Typography>
            <p></p>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Role: {localStorage.getItem('role') == 'admin' ? "Admin" : "Normal user"}
            </Typography>
        </div>

    )
}