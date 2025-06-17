package reservation.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reservation.hotel.model.Hotel;
import reservation.hotel.services.HotelService;




@RestController 
@CrossOrigin
@RequestMapping("/api/hotel")
public class HotelController {
    
    @Autowired
    HotelService hotelService;

    @PostMapping("/cree")
    public void creeHotel(@RequestBody Hotel hotel) {
        hotelService.creeHotel(hotel);
    }
    

    @GetMapping("/")
    public List<Hotel> getHotels(){
        return hotelService.getHotels();
    }

    @GetMapping("/{nom}")
    public Hotel getHotel(@PathVariable String nom) {
        return hotelService.getHotel(nom);
       }
    

    @DeleteMapping("/supprimer")
    public void supprilemHotel(@RequestParam String param) {
        hotelService.supprimerHotel(param);
    }
    
    


}
