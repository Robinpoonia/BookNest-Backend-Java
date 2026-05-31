package com.rpstylish.project.BookNest.repository;

import com.rpstylish.project.BookNest.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
