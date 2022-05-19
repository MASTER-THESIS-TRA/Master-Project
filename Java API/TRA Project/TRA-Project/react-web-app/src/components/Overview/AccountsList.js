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
        let id = "2586b8b5-cf3d-40b8-af12-b00b5224f79b"
        axios.get(`http://localhost:8080/overview/getAgent/${id}`)
            .then((response) => {
                setUserData(response.data)
                console.log("user", response.data)
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
                Balance: {userData.balance}
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Pending Transactions: {userData.pendingTransactions}
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Other:
            </Typography>
        </div>

    )
}