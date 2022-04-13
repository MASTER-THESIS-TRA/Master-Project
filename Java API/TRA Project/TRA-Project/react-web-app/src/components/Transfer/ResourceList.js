import {ResourceModal} from "../Modals/ResourceModal";
import React, {useState} from "react";
import Button from "@mui/material/Button";


export const ResourceList = () => {
    const[showModal, setShowModal] = useState(false);


    const toggleModal = () => {
        setShowModal(!showModal)
    }

    const setResources = (resource) => {
        this.setState({resources: resource})
    }
    return(
        <div>
            <div>
                <p>Table content</p>
            </div>
            <Button variant="contained" onClick={toggleModal}>Add Resource</Button>
            <ResourceModal isOpen={showModal} isClosed={toggleModal}/>
        </div>
    )
}

