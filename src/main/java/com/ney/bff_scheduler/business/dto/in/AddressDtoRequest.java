package com.ney.bff_scheduler.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDtoRequest {

    private String road;
    private String number;
    private String info;
    private String city;
    private String state;
    private String code;
}
