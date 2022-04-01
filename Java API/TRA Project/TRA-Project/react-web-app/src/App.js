import React from 'react';
import {Sidebar} from "./components/Sidebar";
import {Overview} from "./pages/Overview";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Account} from "./pages/Account";

const divStyle = {
    marginLeft: '220px',
};

function App() {
  return (
    <BrowserRouter>
        <Sidebar />
        <div style={divStyle}>
            <Routes>
                <Route path="/" element={<Overview />} />
                <Route path="account" element={<Account />} />
            </Routes>
        </div>
    </BrowserRouter>
  );
}

export default App;
