package dk.diku.TRA.Project.Transfer;

import java.util.List;

public class TransferDto {
    //private String userId;
    public List<ResourceDto> resources;

    public TransferDto(List<ResourceDto> resources) {
        this.resources = resources;
    }

    /*
    public String getUserId() {
        return userId;
    } */

    public List<ResourceDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDto> resources) {
        this.resources = resources;
    }
}
