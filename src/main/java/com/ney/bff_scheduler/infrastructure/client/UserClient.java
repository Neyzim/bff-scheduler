package com.ney.bff_scheduler.infrastructure.client;

import com.ney.bff_scheduler.business.dto.in.AddressDtoRequest;
import com.ney.bff_scheduler.business.dto.in.LoginDtoRequest;
import com.ney.bff_scheduler.business.dto.in.PhoneDtoRequest;
import com.ney.bff_scheduler.business.dto.in.UserDtoRequest;
import com.ney.bff_scheduler.business.dto.out.AddressDtoResponse;
import com.ney.bff_scheduler.business.dto.out.PhoneDtoResponse;
import com.ney.bff_scheduler.business.dto.out.UserDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bffUser", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDtoResponse getUserByEmail(@RequestParam("email") String email,
                                  @RequestHeader("Authorization") String token);

    @PostMapping
    UserDtoResponse saveUser(@RequestBody UserDtoRequest userDto);

    @PostMapping(value = "/login")
    String login(@RequestBody LoginDtoRequest userDto);


    @DeleteMapping(value = "/delete/{email}")
    void deleteUserByEmail(@PathVariable String email,
                           @RequestHeader("Authorization") String token);

    @PutMapping
    UserDtoResponse updateUserInformation(@RequestBody UserDtoRequest userDto,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDtoResponse updateAddress(@RequestBody AddressDtoRequest addressDto,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/phone")
    PhoneDtoResponse updatePhone(@RequestBody PhoneDtoRequest phoneDto,
                                 @RequestParam("id") Long id,
                                 @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDtoResponse saveAddress(@RequestBody AddressDtoRequest addressDto,
                                  @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    PhoneDtoResponse saveNewPhone(@RequestBody PhoneDtoRequest phoneDto,
                                 @RequestHeader("Authorization") String token);
}

