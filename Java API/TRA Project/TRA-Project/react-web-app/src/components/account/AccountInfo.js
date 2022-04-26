import React, {Component} from "react";

import {fetchAccountInfo} from "../../api/AccountAPI";

class AccountInfo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
    }


    render() {
        return (
            <div>
                {}
            </div>
        )
    }
}