import {CustomTable} from "../CustomTable";

const cols = [
    'Type', 'Date', 'Net'
];

const rows = [
    ['Transfer', '13/04-2022', '-40 Coffee Bag (500g), +200$'],
    ['Transform', '03/04-2022', '-20 Roasted Coffee (kg), -40 Paper Bag (pcs), +40 Coffee Bag (500g)'],
    ['Transfer', '01/04-2022', '-10$, +40 Paper Bag (pcs)'],
    ['Transfer', '01/04-2022', '-100$, +20 Roasted Coffee (kg)']
];

export const RecentTransactions = () => {
    return (
        <div>
            <CustomTable rows={rows} cols={cols}/>
        </div>
    )
}