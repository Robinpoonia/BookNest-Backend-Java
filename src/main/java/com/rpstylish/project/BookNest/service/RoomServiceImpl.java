package com.rpstylish.project.BookNest.service;

import com.rpstylish.project.BookNest.dto.RoomDto;
import com.rpstylish.project.BookNest.entity.Hotel;
import com.rpstylish.project.BookNest.entity.Room;
import com.rpstylish.project.BookNest.exception.ResourceNotFoundException;
import com.rpstylish.project.BookNest.repository.HotelRepository;
import com.rpstylish.project.BookNest.repository.InventoryRepository;
import com.rpstylish.project.BookNest.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.management.PlatformLoggingMXBean;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InvertoryService invertoryService;

    @Override
    public RoomDto creatNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "hotel not found with id " + hotelId
                        )
                );

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        Room savedRoom = roomRepository.save(room);

        if(hotel.getActive()){
            invertoryService.initializeRoomForAYear(room);
        }

         return modelMapper.map(savedRoom, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomInHotel(Long hotelId) {
        log.info("Getting all room in hotel with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("hotel not found with id " + hotelId));
        return hotel.getRooms().stream()
                .map((element) -> modelMapper.map(element,RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Creating a room with id: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with id " + roomId));
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting a room with id: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with id " + roomId));
        invertoryService.deleteFutureInventories(room);
        roomRepository.deleteById(roomId);

    }
}
