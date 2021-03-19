package com.samus.irdigital.backend.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ErrorDetailMessage {
    private String step;
    private String message;
}
