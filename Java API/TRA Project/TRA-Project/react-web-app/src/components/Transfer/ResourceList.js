import Box from "@mui/material/Box";
import List from "@mui/material/List";
import {ListItem, ListItemText} from "@mui/material";
import {useState} from "react";

export const ResourceList = () => {
    //const[resources, setResources] = useState({resources: []})

    const resources = [
        {name: "Resource 1", value: 5},
        {name: "Resource 2", value: 5}
    ]


    //setResources(list);

    return (
        <div>
            <p>Hi</p>
            <Box>
                <List>
                    <ResourceListItem resources={resources}/>
                </List>
            </Box>
        </div>
    )
}

const ResourceListItem = (resources) => {
    return(
        resources.map(
            console.log(resources.name),
            <ListItem>
                <ListItemText primary={resources.name} />
            </ListItem>
        )
    )
}