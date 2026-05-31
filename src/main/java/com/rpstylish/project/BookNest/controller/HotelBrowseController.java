package com.rpstylish.project.BookNest.controller;

import com.rpstylish.project.BookNest.dto.HotelDto;
import com.rpstylish.project.BookNest.dto.HotelInfoDto;
import com.rpstylish.project.BookNest.dto.HotelSearchRequest;
import com.rpstylish.project.BookNest.service.HotelService;
import com.rpstylish.project.BookNest.service.InvertoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {
    private final InvertoryService invertoryService;
    private final HotelService hotelService;


    @PostMapping("/search")
    public ResponseEntity<Page<HotelDto>> serachHotel(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelDto> page = invertoryService.searchHotel(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
