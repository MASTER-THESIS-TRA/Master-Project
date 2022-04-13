import logo from '../../Resources/swap.png'
import Container from "@mui/material/Container";

const imgStyle = {
    height: '100%',
    display: 'block',
    margin: 'auto',
    maxWidth: '100%'
}

const divStyle = {
    overflow: 'hidden'
}

export const ExchangeLogo = () => {
    return (
        <div style={divStyle}>
            <img style={imgStyle} src={logo}/>
        </div>
    )
}