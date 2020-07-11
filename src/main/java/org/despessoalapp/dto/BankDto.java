package org.despessoalapp.dto;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
public class BankDto {

    @Size(message = "BankName may not greater then 10 character", max = 10)
    @NotBlank(message = "Name may not be blank")
    private String name;

    @Size(message = "Description may not greater then 30 character", max = 30)
    @NotBlank(message = "Description may not be blank")
    private String description;

    @NotBlank(message = "Description may not be blank")
    private LocalDate paymentDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(LocalDate paymentDay) {
        this.paymentDay = paymentDay;
    }
}
