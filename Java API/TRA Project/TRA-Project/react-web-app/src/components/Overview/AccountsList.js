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
        axios.get("http://localhost:8080/agent/getAgentInfo")
            .then((response) => {
                setUserData(response.data)
                console.log(response.data.json())
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
                Account ID: {userData.id}
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