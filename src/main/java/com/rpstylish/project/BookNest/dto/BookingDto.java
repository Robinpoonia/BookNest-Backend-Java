package com.rpstylish.project.BookNest.dto;

import com.rpstylish.project.BookNest.entity.Guest;
import com.rpstylish.project.BookNest.entity.Hotel;
import com.rpstylish.project.BookNest.entity.Room;
import com.rpstylish.project.BookNest.entity.User;
import com.rpstylish.project.BookNest.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private BookingStatus bookingStatus;
    private Set<GuestDto> Guests;
    private Integer roomCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
