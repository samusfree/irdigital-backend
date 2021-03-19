package com.samus.irdigital.backend.api.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Client {
    private UUID id;
    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;
    @NotNull(message = "Age cannot be empty")
    @Range(max = 150, message = "Age must be between 0 and 150")
    private Integer age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Born date cannot be empty")
    @PastOrPresent(message = "Born date cannot be in the future")
    @Schema(example = "07/10/1989", type = "string", format = "date")
    private LocalDate bornDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(example = "07/10/1989", type = "string", format = "date")
    private LocalDate possibleDateOfDeath;
}
