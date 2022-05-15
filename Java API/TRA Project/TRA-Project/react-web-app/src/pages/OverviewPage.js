import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import {Paper} from "@mui/material";
import {PendingTransactions} from "../components/Overview/PendingTransactions";
import {RecentTransactions} from "../components/Overview/RecentTransactions";
import {AccountList} from "../components/Overview/AccountsList";

export const OverviewPage = () => {
    return (
        <div>
            <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                <Grid container spacing={3}>
                    {/* AccountList */}
                    <Grid item xs={12} md={8} lg={7}>
                        <Paper
                            sx={{
                                p: 2,
                                display: 'flex',
                                flexDirection: 'column',
                                height: 300,
                                overflow: 'auto'
                            }}
                        >
                            <AccountList/>
                        </Paper>
                    </Grid>
                    {/* RecentTransactions */}
                    <Grid item xs={12} md={8} lg={5}>
                        <Paper
                            sx={{
                                p: 2,
                                display: 'flex',
                                flexDirection: 'column',
                                height: 300,
                                overflow: 'hidden',
                            }}
                        >
                            <RecentTransactions/>
                        </Paper>
                    </Grid>
                    {/* PendingTransactions */}
                    <Grid item xs={12}>
                        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column', maxHeight:500, height: 400, overflow: 'auto' }}>
                            <PendingTransactions/>
                        </Paper>
                    </Grid>
                </Grid>
            </Container>
        </div>
    )
}