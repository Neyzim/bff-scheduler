package com.ney.bff_scheduler.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDtoResponse {

    private Long id;
    private String number;
    private String ddd;
}
