package com.example.cpuserservice.mappers;

import com.example.cpuserservice.dtos.UserProfileRequestDto;
import com.example.cpuserservice.dtos.UserProfileResponseDto;
import com.example.cpuserservice.dtos.UserSignInDto;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.models.UserProfile;

public class UserProfileMapper {

    public static UserProfileResponseDto mapToProfileResponseDto(UserProfile userProfile) {
        UserProfileResponseDto dto = new UserProfileResponseDto();

        // Set UserSignInDto
        User user = userProfile.getUser();
        UserSignInDto userSignInDto = new UserSignInDto();
        userSignInDto.setUsername(user.getUsername());
        // The password would typically not be sent in a response, so it's often excluded

        dto.setUserSignInDto(userSignInDto);
        dto.setFirstName(userProfile.getFirstName());
        dto.setLastName(userProfile.getLastName());
        dto.setPhoneNumber(userProfile.getPhoneNumber()); // Assuming phone number is stored as a String in the entity
        dto.setAddress(userProfile.getAddress());
        dto.setProfilePictureUrl(userProfile.getProfielPictureUrl());

        return dto;
    }

    public static UserProfile mapToUserProfile(UserProfileRequestDto dto, User user) {

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());
        userProfile.setPhoneNumber(dto.getPhoneNumber());
        userProfile.setAddress(dto.getAddress());
        userProfile.setProfielPictureUrl(dto.getProfilePictureUrl());
        return userProfile;
    }
}
