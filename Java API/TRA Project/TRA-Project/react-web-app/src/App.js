import React, {Component} from 'react';
import {BrowserRouter} from "react-router-dom";
import SignIn from "./components/login/login";
import Dashboard from "./components/Dashboard";


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
                {this.state.loggedIn ? <Dashboard /> : <SignIn handleLogin={this.handleLogin}/>}
            </BrowserRouter>
        );
    }
}

export default App;
