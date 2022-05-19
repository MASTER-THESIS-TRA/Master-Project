import Title from "../Title";
import Typography from "@mui/material/Typography";
import {useEffect, useState} from "react";
import {fetchAccountInfo} from "../../api/AccountAPI";
import axios from "axios";

export const AccountList = () => {
    const[userData, setUserData] = useState({});

    useEffect(() => {
        fetchAccountInfo()
    }, [])

    const fetchAccountInfo = () => {
        let id = localStorage.getItem('user');
        axios.get(`http://localhost:8080/overview/getAgent/${id}`)
            .then((response) => {
                setUserData(response.data)
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }
    return (
        <div>
            <Title>Account info</Title>
            <Typography component="p" variant="h4">
                Hello {userData.name}!
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Account ID: {localStorage.getItem('user')}
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Email: {userData.email}
            </Typography>
        </div>

    )
}