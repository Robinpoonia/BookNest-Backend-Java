package com.rpstylish.project.BookNest.service;

import com.rpstylish.project.BookNest.dto.HotelDto;
import com.rpstylish.project.BookNest.entity.Hotel;
import com.rpstylish.project.BookNest.entity.Room;
import com.rpstylish.project.BookNest.exception.ResourceNotFoundException;
import com.rpstylish.project.BookNest.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InvertoryService invertoryService;

    @Override
    public HotelDto createNewHostel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with id {}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting The Hotel with id {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("hotel not found with id " + id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Getting The Hotel with id {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("hotel not found with id " + id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long hotelId) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("hotel not found with id " + hotelId));

        hotelRepository.deleteById(hotelId);
        for(Room room: hotel.getRooms()){
            invertoryService.deleteFutureInventories(room);
        }


    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Getting The Hotel with id {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("hotel not found with id " + hotelId));
        hotel.setActive(true);

        //assuming only do it once
        for(Room room: hotel.getRooms()){
            invertoryService.initializeRoomForAYear(room);
        }
    }
}

