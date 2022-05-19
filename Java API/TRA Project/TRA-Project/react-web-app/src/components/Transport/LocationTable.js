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


export const LocationTable = ({columns, rows, showPagination, maxHeight}) => {
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(event.target.value);
        setPage(0);
    };

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
                                let key = Math.random();
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={key}>
                                        {columns.map((column) => {
                                            const value = row[column.id];
                                            if(column.id === "location") {
                                                return(
                                                    <TableCell key={column.id} align={column.align}>
                                                        <Button href={`https://google.com/maps/place/${row.xCoord},${row.yCoord}`} target="_blank" rel="noopener noreferrer">See location</Button>
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
        </div>

    );
}

