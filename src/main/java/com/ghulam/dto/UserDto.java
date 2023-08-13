package com.ghulam.dto;

import com.ghulam.enums.Gender;
import com.ghulam.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String contactNo;

    public UserDto toUserDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.contactNo = user.getContactNo();
        return this;
    }
}