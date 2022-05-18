package dk.diku.TRA.Project.Dtos;

public class AgentDto {
    public String name;
    public String email;
    public String password;

    public AgentDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
