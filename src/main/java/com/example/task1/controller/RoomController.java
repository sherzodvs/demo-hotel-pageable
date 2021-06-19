package com.example.task1.controller;

import com.example.task1.entity.Hotel;
import com.example.task1.entity.Room;
import com.example.task1.payload.RoomDto;
import com.example.task1.repository.HotelRepository;
import com.example.task1.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;
//    Hotel Id bo'yicha page qilib olish
    @GetMapping("/byhotelid/{hotelId}")
    public Page<Room> getByHotelId(@PathVariable Integer hotelId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
       return roomRepository.findByHotelId(hotelId,pageable);
    }

//Barchasini page qilib olsih
    @GetMapping
    public Page<Room> getAll(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        return roomRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Room get(@PathVariable Integer id){
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()){
            Room room = roomOptional.get();
            return room;
        }
        return new Room();
    }

    @PostMapping
    public String add(@RequestBody RoomDto roomDto){
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDto.getHotelId());
        if (hotelOptional.isPresent()){
            Hotel hotel = hotelOptional.get();
            room.setHotel(hotel);
            roomRepository.save(room);
        }
        return "Saqlandi!!!";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()){
            Room room = roomOptional.get();
            room.setNumber(room.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            Optional<Hotel> hotelOptional = hotelRepository.findById(roomDto.getHotelId());
            if (hotelOptional.isPresent()){
                Hotel hotel = hotelOptional.get();
                room.setHotel(hotel);
                roomRepository.save(room);
            }
        }
        return "O'zgartirildi!!!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        roomRepository.deleteById(id);
        return "O'chirildi!!!";
    }

}
