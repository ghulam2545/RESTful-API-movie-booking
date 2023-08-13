package com.ghulam.model;

import com.ghulam.dto.UserDto;
import com.ghulam.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "contact_no")
    private String contactNo;

    public User toUser(UserDto dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.gender = dto.getGender();
        this.contactNo = dto.getContactNo();
        return this;
    }
}
