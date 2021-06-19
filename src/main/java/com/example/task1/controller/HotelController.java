package com.example.task1.controller;

import com.example.task1.entity.Hotel;
import com.example.task1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getAll(){
        return hotelRepository.findAll();
    }
    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Integer id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()){
            return hotelOptional.get();
        }
        return new Hotel();
    }

    @PostMapping
    public String add(@RequestBody Hotel hotel){
        Hotel hotelNew = new Hotel();
        hotelNew.setName(hotel.getName());
        hotelRepository.save(hotelNew);
        return "Saqlandi!!!";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()){
            Hotel hotelNew = hotelOptional.get();
            hotelNew.setName(hotel.getName());
            return "O'zgartirildi!!!";
        }
        return "Id topilmadi";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "O'chirildi!!!";
    }
}
