import logo from '../../Resources/transform.png'
import Container from "@mui/material/Container";

const imgStyle = {
    paddingTop: '100%',
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