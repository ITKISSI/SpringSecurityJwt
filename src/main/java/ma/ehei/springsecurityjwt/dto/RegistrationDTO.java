package ma.ehei.springsecurityjwt.dto;

import lombok.Data;

@Data
public class RegistrationDTO {

    private String username;
    private String password;

    public  RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return "user's Username="+this.username+" Password="+this.password;
    }

}
