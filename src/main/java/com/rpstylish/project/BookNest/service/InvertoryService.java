package com.rpstylish.project.BookNest.service;

import com.rpstylish.project.BookNest.entity.Room;

public interface InvertoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
