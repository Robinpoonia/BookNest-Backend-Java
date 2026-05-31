package com.rpstylish.project.BookNest.service;

import com.rpstylish.project.BookNest.dto.BookingDto;
import com.rpstylish.project.BookNest.dto.BookingRequest;
import com.rpstylish.project.BookNest.dto.GuestDto;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
