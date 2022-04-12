import {AgentBox} from "../components/Transfer/AgentBox";
import {ResourceList} from "../components/Transfer/ResourceList";
import {AddButton} from "../components/addButton";

export const TransferPage = () => {
    return(
        <div>
           <div>
               <AgentBox agent={"Agent 1"}/>
           </div>
            <ResourceList/>
            <AddButton/>
            <ExchangeLogo/>
            <div>
                <AgentBox agent={"Agent 2"}/>
            </div>
            <ResourceList/>
            <AddButton/>
        </div>
    )
}