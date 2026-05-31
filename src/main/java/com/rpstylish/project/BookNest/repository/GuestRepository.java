package com.rpstylish.project.BookNest.repository;

import com.rpstylish.project.BookNest.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}