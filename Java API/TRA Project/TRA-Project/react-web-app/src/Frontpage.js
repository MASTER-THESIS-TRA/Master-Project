import {Sidebar} from "./components/Sidebar";
import {Route, Routes} from "react-router-dom";
import SignIn from "./components/login/login";
import {AccountPage} from "./pages/AccountPage";
import {TransferPage} from "./pages/TransferPage";
import {TransformPage} from "./pages/TransformPage";
import React from "react";
import {OverviewPage} from "./pages/OverviewPage";

export const Frontpage = () => {

    const divStyle = {
        marginLeft: '220px',
    };

    return(
        <div>
            <Sidebar />
            <div style={divStyle}>
                <Routes>
                    <Route path="/" element={<OverviewPage />} />
                    <Route path="account" element={<AccountPage />} />
                    <Route path="transfer" element={<TransferPage />} />
                    <Route path="transform" element={<TransformPage />} />
                </Routes>
            </div>
        </div>
    )
}