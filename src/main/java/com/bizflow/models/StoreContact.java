package com.bizflow.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreContact {

    @NotBlank(message = "Address must not be blank")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Phone number must be a valid 10-digit Indian mobile number"
    )
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
