package upc.edu.LoggyAPI.user.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String dni;
}
