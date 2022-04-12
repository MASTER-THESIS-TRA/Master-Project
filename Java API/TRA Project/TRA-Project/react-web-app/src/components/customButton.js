import {useState} from "react";


export const CustomButton = (props) => {
    const[showModal, setShowModal] = useState(false)
    return(
        <div>
            <button onClick={props.clickAction} text={props.text}></button>
        </div>
    )
}