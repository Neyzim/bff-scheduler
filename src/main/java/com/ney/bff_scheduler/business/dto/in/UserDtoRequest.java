package com.ney.bff_scheduler.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoRequest {

    private String name;
    private String email;
    private String password;
    private List<AddressDtoRequest> addresses;
    private List<PhoneDtoRequest> phones;
}
