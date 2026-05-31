package com.rpstylish.project.BookNest.dto;

import com.rpstylish.project.BookNest.entity.User;
import com.rpstylish.project.BookNest.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;

}
