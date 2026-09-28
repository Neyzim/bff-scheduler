package com.ney.bff_scheduler.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDtoRequest {

    private String email;
    private String password;
}
