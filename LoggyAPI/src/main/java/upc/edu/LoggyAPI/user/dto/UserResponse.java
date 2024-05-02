package upc.edu.LoggyAPI.user.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String username;
    @Email
    private String email;
    private String dni;
}
