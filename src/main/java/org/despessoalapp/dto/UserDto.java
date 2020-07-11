package org.despessoalapp.dto;

import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
public class UserDto {

    @Size(message = "FullName may not greater then 30 character", max = 30)
    @NotBlank(message = "FullName may not be blank")
    private String fullName;

    @Size(message = "Password may not greater then 30 character", max = 20)
    @NotBlank(message = "Password may not be blank")
    private String password;

    @Size(message = "E-mail may not greater then 30 character", max = 30)
    @Email(message = "E-mail not valid")
    private String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
