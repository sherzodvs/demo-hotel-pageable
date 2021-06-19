package com.example.task1.payload;

import lombok.Data;

@Data
public class RoomDto {
    Integer id;
    Integer number;
    Integer floor;
    double size;
    Integer hotelId;
}
