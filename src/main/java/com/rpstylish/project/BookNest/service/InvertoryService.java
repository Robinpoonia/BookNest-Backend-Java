package com.rpstylish.project.BookNest.service;

import com.rpstylish.project.BookNest.dto.HotelDto;
import com.rpstylish.project.BookNest.dto.HotelSearchRequest;
import com.rpstylish.project.BookNest.entity.Room;
import org.springframework.data.domain.Page;

public interface InvertoryService {
    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotel(HotelSearchRequest hotelSearchRequest);
}
