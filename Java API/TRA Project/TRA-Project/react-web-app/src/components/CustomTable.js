import TableContainer from "@mui/material/TableContainer";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import * as React from "react";

export const CustomTable = (props) => {
    return (
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>{props.cols[0]}</TableCell>
                        {props.cols.slice(1).map((column) => (
                            <TableCell align="right">{column}</TableCell>
                        ))}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.rows.map((row) => (
                        <TableRow
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                            <TableCell>{row[0]}</TableCell>
                            {row.slice(1).map((element) => (
                                <TableCell align="right">{element}</TableCell>
                            ))}
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}