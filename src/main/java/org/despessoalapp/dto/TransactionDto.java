package org.despessoalapp.dto;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
public class TransactionDto {

    @NotBlank(message = "BankId may not be blank")
    private Long bankId;

    @NotBlank(message = "UserId may not be blank")
    private Long userId;

    @NotBlank(message = "Operation may not be blank")
    private String operation;

    @Size(message = "Description may not greater then 30 character", max = 30)
    @NotBlank(message = "Description may not be blank")
    private String description;

    @NotBlank(message = "DebCred may not be blank")
    private String debCred;

    @NotBlank(message = "Value may not be blank")
    private Double value;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebCred() {
        return debCred;
    }

    public void setDebCred(String debCred) {
        this.debCred = debCred;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
