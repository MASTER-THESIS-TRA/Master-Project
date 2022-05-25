import React, {Component, useEffect, useState} from 'react';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import SignIn, {LoginPage} from "./components/login/login";
import {Dashboard} from "./components/Dashboard";
import axios from "axios";



export const App = () => {
    const[loggedIn, setLoggedIn] = useState(false);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [user, setUser] = useState()

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };
    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleLogout = () => {
        setUser({});
        setEmail("");
        setPassword("");
        localStorage.clear();
        window.location.reload()
    };

    const handleSubmit = async e => {
        e.preventDefault();
        const user = {
            email: email,
            password: password
        };
        await axios.post("http://localhost:8080/login/validateLogin", user, {
            headers: {
                'Accept': 'application/json',
                'content-type': 'application/json'
            }})
            .then((response) => {
                    if(response.data.error != "error") {
                        localStorage.setItem('user', response.data.user)
                        setUser(response.data.user);
                        setLoggedIn(true);
                        if(response.data.role == "Admin") {
                            localStorage.setItem('role', 'admin')
                        }
                        //localStorage.setItem('user', response.data);
                    } else {
                        alert("wrong email or password");
                    }
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

    if(!loggedIn) {
        return (
            <LoginPage handleEmailChange={handleEmailChange}
                       handlePasswordChange={handlePasswordChange} handleSubmit={handleSubmit}/>
        )
    }

    return(
        <div>
            <Dashboard handleLogout={handleLogout}/>
        </div>

    )
}

