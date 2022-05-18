import React, {Component, useEffect, useState} from 'react';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import SignIn, {LoginPage} from "./components/login/login";
import {Dashboard} from "./components/Dashboard";
import axios from "axios";
import {SignupPage} from "./pages/SignupPage";



export const App = () => {
    const[loggedIn, setLoggedIn] = useState(false);
    const[signUp, setSignUp] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [user, setUser] = useState()

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };
    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSignUpChange = () => {
        setSignUp(!signUp);
    };

    const handleLogout = () => {
        setUser({});
        setUsername("");
        setPassword("");
        localStorage.clear();
        window.location.reload()
    };

    const handleSubmit = async e => {
        e.preventDefault();
        const user = {
            username: username,
            password: password
        };
        await axios.post("http://localhost:8080/login/intialLogin", user, {
            headers: {
                'Accept': 'application/json',
                'content-type': 'application/json'
            }})
            .then((response) => {
                    console.log(response.data);
                    setUser(response.data);
                    setLoggedIn(true);
                    localStorage.setItem('user', response.data);
                }
            )
            .catch((error) => {
                console.log(error);
            })
    }

    useEffect(() => {
        const loggedInUser = localStorage.getItem("user");
        if (loggedInUser) {
            const foundUser = JSON.parse(JSON.stringify(loggedInUser));
            setLoggedIn(true);
            setUser(foundUser);
        }
    }, []);

    if(!loggedIn && !signUp) {
        return(
            <LoginPage handleSignUpChange={handleSignUpChange} handleUsernameChange={handleUsernameChange} handlePasswordChange={handlePasswordChange} handleSubmit={handleSubmit}/>
        )
    } else if(!loggedIn && signUp) {
        return(
            <SignupPage  handleSignUpChange={handleSignUpChange}/>
        )
    }

    return(
        <div>
            <Dashboard handleLogout={handleLogout}/>
        </div>

    )
}

