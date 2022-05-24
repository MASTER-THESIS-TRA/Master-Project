import TableContainer from "@mui/material/TableContainer";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import * as React from "react";
import TablePagination from "@mui/material/TablePagination";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import {EventModal} from "./EventModal";
import {useState} from "react";


export const EventTable = ({columns, rows, showPagination, maxHeight}) => {
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);
    const[showModal, setShowModal] = useState(false);
    const[events, setEvents] = useState();

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(event.target.value);
        setPage(0);
    };


    const handleOpen = (body) => {
        setEvents(body)
        setShowModal(true);
    }
    const handleClose = () => {
        setShowModal(false);
    }


    return (
        <div>
            <TableContainer sx={{ maxHeight: maxHeight, overflowX: 'hidden'}}>
                <Table stickyHeader={true} aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={column.align}
                                    style={{ minWidth: column.minWidth }}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.id}>
                                        {columns.map((column) => {
                                            const value = row[column.id];
                                            if(column.id === "body") {
                                                return(
                                                    <TableCell key={column.id} align={column.align}>
                                                        <Button onClick={() => handleOpen(row.body)}>See details</Button>
                                                    </TableCell>
                                                )
                                            }
                                            return (
                                                <TableCell key={column.id} align={column.align}>
                                                    {column.format && typeof value === 'number'
                                                        ? column.format(value)
                                                        : value}
                                                </TableCell>
                                            );
                                        })}
                                    </TableRow>
                                );
                            })}
                    </TableBody>
                </Table>
            </TableContainer>
            { showPagination ? <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            /> : null }
            <EventModal onClose={handleClose} open={showModal} events={events} />
        </div>
    );
}

