package com.samus.irdigital.backend.api.dto;

import com.samus.irdigital.backend.api.enums.CodeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RestResponse<T> {
    private CodeEnum code;
    private T data;
    private ErrorDetailMessage detail;
}
