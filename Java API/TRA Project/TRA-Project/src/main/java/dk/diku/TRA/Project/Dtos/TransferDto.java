package dk.diku.TRA.Project.Dtos;

public class TransferDto {
    public String sender;
    public String receiver;
    public String resourceName;
    public Integer amount;

    public TransferDto(String sender, String receiver, String resourceName, Integer amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.resourceName = resourceName;
        this.amount = amount;
    }
}
