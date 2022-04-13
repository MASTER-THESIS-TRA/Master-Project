import {AgentBox} from "../components/Transfer/AgentBox";
import {ResourceList} from "../components/Transfer/ResourceList";
import {CustomButton} from "../components/customButton";
import {ExchangeLogo} from "../components/Transfer/ExchangeLogo";

export const TransferPage = () => {
    return(
        <div>
           <div>
               <AgentBox agent={"Agent 1"}/>
           </div>
            <ResourceList listObj1={listObj1}/>
            <CustomButton resourceList=listObj1/>
            <ExchangeLogo/>
            <div>
                <AgentBox agent={"Agent 2"}/>
            </div>
            <ResourceList listObj2={listObj2}/>
            <CustomButton resourceList = listObj2/>
        </div>
    )
}