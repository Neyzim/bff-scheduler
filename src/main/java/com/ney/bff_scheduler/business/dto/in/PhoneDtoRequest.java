package com.ney.bff_scheduler.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDtoRequest {

    private String number;
    private String ddd;
}
