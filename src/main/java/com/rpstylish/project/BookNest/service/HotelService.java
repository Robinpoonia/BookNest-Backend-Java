package com.rpstylish.project.BookNest.service;

import com.rpstylish.project.BookNest.dto.HotelDto;
import com.rpstylish.project.BookNest.entity.Hotel;

public interface HotelService {
    HotelDto createNewHostel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long hotelId);
}
