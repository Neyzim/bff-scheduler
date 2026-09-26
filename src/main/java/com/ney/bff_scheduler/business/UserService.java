package com.ney.bff_scheduler.business;

import com.ney.bff_scheduler.business.dto.in.AddressDtoRequest;
import com.ney.bff_scheduler.business.dto.in.PhoneDtoRequest;
import com.ney.bff_scheduler.business.dto.in.UserDtoRequest;
import com.ney.bff_scheduler.business.dto.out.AddressDtoResponse;
import com.ney.bff_scheduler.business.dto.out.PhoneDtoResponse;
import com.ney.bff_scheduler.business.dto.out.UserDtoResponse;
import com.ney.bff_scheduler.infrastructure.client.UserClient;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;


    public UserDtoResponse saveUser(UserDtoRequest userDto){
       return userClient.saveUser(userDto);
    }

    public String login (UserDtoRequest userDto){
        return userClient.login(userDto);
    }

    public UserDtoResponse getUserByEmail(String email, String token){
        return userClient.getUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token){
        userClient.deleteUserByEmail(email, token);
    }

    public UserDtoResponse updateUserData(String token, UserDtoRequest userDto){
       return userClient.updateUserInformation(userDto,token);
    }

    public AddressDtoResponse updateAddress(Long addressId, AddressDtoRequest addressDto, String token){
        return userClient.updateAddress(addressDto, addressId, token);
    }

    public PhoneDtoResponse updatePhone(Long phoneId, PhoneDtoRequest phoneDto, String token){
        return userClient.updatePhone(phoneDto, phoneId, token);
    }

    public AddressDtoResponse saveNewAddress(String token, AddressDtoRequest addressDto){
        return userClient.saveAddress(addressDto, token);
    }

    public PhoneDtoResponse saveNewPhone(String token, PhoneDtoRequest phoneDto){
        return  userClient.saveNewPhone(phoneDto, token);
    }
}
