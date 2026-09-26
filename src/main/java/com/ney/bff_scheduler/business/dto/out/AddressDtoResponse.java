package com.ney.bff_scheduler.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDtoResponse {

    private Long id;
    private String road;
    private String number;
    private String info;
    private String city;
    private String state;
    private String code;
}
