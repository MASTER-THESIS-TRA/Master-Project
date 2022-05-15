import {CustomTable} from "../CustomTable";
import Title from "../Title";
import * as React from "react";

const cols = [
    { id: 'type', label: 'Type', minWidth: 170 },
    { id: 'date', label: 'Date', minWidth: 170 },
    { id: 'net', label: 'Net', minWidth: 170 },
];

function createData(type, date, net) {
    return { type, date, net };
}

/*
const rows = [
    createData('Transfer', '13/04-2022', '-40 Coffee Bag (500g), +200$'),
    createData('Transform', '03/04-2022', '-20 Roasted Coffee (kg), -40 Paper Bag (pcs), +40 Coffee Bag (500g)'),
    createData('Transfer', '01/04-2022', '-10$, +40 Paper Bag (pcs)'),
    createData('Transfer', '01/04-2022', '-100$, +20 Roasted Coffee (kg)')
];

 */
const rows = [
    createData('Cupcake', 305, 3.7),
    createData('Donut', 452, 25.0),
    createData('Eclair', 262, 16.0),
    createData('Frozen yoghurt', 159, 6.0),
    createData('Gingerbread', 356, 16.0),
    createData('Honeycomb', 408, 3.2),
    createData('Ice cream sandwich', 237, 9.0),
    createData('Jelly Bean', 375, 0.0),
    createData('KitKat', 518, 26.0),
    createData('Lollipop', 392, 0.2),
    createData('Marshmallow', 318, 0),
    createData('Nougat', 360, 19.0),
    createData('Oreo', 437, 18.0),
];

export const RecentTransactions = () => {
    return (
        <div>
            <Title>Recent Transactions</Title>
            <CustomTable rows={rows} columns={cols} showPagination={false} maxHeight={240}/>
        </div>
    )
}