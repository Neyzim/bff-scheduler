package com.ney.bff_scheduler.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoResponse {

    private String name;
    private String email;
    private String password;
    private List<AddressDtoResponse> addresses;
    private List<PhoneDtoResponse> phones;
}
