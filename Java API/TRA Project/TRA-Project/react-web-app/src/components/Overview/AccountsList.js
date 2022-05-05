import Title from "../Title";
import Typography from "@mui/material/Typography";

export const AccountList = (props) => {
    return (
        <div>
            <Title>Account info</Title>
            <Typography component="p" variant="h4">
                Hello {props.name}!
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Account ID: {props.accountid}
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Balance: {props.balance}
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Pending Transactions: {props.pendingtransaction}
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Other:
            </Typography>
        </div>

    )
}