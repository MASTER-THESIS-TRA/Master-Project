import {ResourceList} from "./ResourceList";

export const AgentBox = ({ setFormData }) => {
    return (
        <div>
            <div>
                <p>Actor name</p>
            </div>
            <div>
                <ResourceList setFormData={setFormData} />
            </div>
        </div>
    )
}