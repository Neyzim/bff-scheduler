package com.ney.bff_scheduler.controller;

import com.ney.bff_scheduler.business.UserService;
import com.ney.bff_scheduler.business.dto.in.AddressDtoRequest;
import com.ney.bff_scheduler.business.dto.in.LoginDtoRequest;
import com.ney.bff_scheduler.business.dto.in.PhoneDtoRequest;
import com.ney.bff_scheduler.business.dto.in.UserDtoRequest;

import com.ney.bff_scheduler.business.dto.out.AddressDtoResponse;
import com.ney.bff_scheduler.business.dto.out.PhoneDtoResponse;
import com.ney.bff_scheduler.business.dto.out.UserDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "BFF User controller", description = "Saving and User Login")
public class UserController {

    private final UserService userService;


    @PostMapping
    @Operation(summary = "Save User", description = "Communicates with User Api to create a New User")
    @ApiResponse(responseCode = "200", description = "User Saved")
    @ApiResponse(responseCode = "400", description = "User already exists")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<UserDtoResponse> saveUser(@RequestBody UserDtoRequest userDto){
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PostMapping(value = "/login")
    @Operation(summary = "Login User", description = "Communicates with User Api to authenticate User")
    @ApiResponse(responseCode = "200", description = "User authenticated")
    @ApiResponse(responseCode = "401", description = "Invalid Credentials")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public String login(@RequestBody LoginDtoRequest userDto){
       return userService.login(userDto);
    }

    @GetMapping
    @Operation(summary = "Search User Data Using Email", description = "Communicates with User Api to get User Information")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User Not found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<UserDtoResponse> getUserByEmail(@RequestParam("email") String email,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.getUserByEmail(email, token));
    }

    @DeleteMapping(value = "/delete/{email}")
    @Operation(summary = "Delete User using id", description = "Communicates with User Api to delete a User")
    @ApiResponse(responseCode = "200", description = "User deleted")
    @ApiResponse(responseCode = "404", description = "User nou found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email,
                                                  @RequestHeader("Authorization") String token){
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update User information", description = "Communicates with User Api to update a User Information")
    @ApiResponse(responseCode = "200", description = "Information updated and saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<UserDtoResponse> updateUserInformation(@RequestBody UserDtoRequest userDto,
                                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateUserData(token, userDto));
    }

    @PutMapping("/address")
    @Operation(summary = "update user Address", description = "Communicates with User Api to update a User Address")
    @ApiResponse(responseCode = "200", description = "Address updated and Saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<AddressDtoResponse> updateAddress(@RequestBody AddressDtoRequest addressDto,
                                                           @RequestParam("id") Long id,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateAddress(id, addressDto, token));
    }

    @PutMapping("/phone")
    @Operation(summary = "update user Phone", description = "Communicates with User Api to update a User Phone")
    @ApiResponse(responseCode = "200", description = "Phone updated and Saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<PhoneDtoResponse> updatePhone(@RequestBody PhoneDtoRequest phoneDto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updatePhone(id, phoneDto, token));
    }

    @PostMapping("/address")
    @Operation(summary = "save user Address", description = "Communicates with User Api to save a User Address")
    @ApiResponse(responseCode = "200", description = "Address Saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<AddressDtoResponse> saveAddress(@RequestBody AddressDtoRequest addressDto,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.saveNewAddress(token, addressDto));
    }

    @PostMapping("/phone") @Operation(summary = "update user Phone", description = "Communicates with User Api to update a User Phone")
    @ApiResponse(responseCode = "200", description = "Phone Saved")
    @ApiResponse(responseCode = "400", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<PhoneDtoResponse> saveNewPhone(@RequestBody PhoneDtoRequest phoneDto,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.saveNewPhone(token, phoneDto));
    }
}
