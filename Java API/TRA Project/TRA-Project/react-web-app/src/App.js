import React, {Component} from 'react';
import {Sidebar} from "./components/Sidebar";

import {BrowserRouter, Route, Routes} from "react-router-dom";

import {OverviewPage} from "./pages/OverviewPage";
import {AccountPage} from "./pages/AccountPage";
import {TransferPage} from "./pages/TransferPage";
import {TransformPage} from "./pages/TransformPage";
import SignIn from "./components/login/login";
import {Frontpage} from "./Frontpage";


export class App extends Component {
    state = {
        loggedIn: false
    }

    handleLogin = (loginState) => {
        this.setState({loggedIn: loginState})
    }
    render() {
        return (
            <BrowserRouter>
                {this.state.loggedIn ? <Frontpage /> : <SignIn handleLogin={this.handleLogin}/>}
            </BrowserRouter>
        );
    }
}

export default App;
