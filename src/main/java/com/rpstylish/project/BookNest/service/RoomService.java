package com.rpstylish.project.BookNest.service;

import com.rpstylish.project.BookNest.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto creatNewRoom(Long hotelId, RoomDto roomDto);

    List<RoomDto> getAllRoomInHotel(Long hotelId);

    RoomDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

}
