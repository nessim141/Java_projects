package reservation.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reservation.hotel.model.Hotel;
import reservation.hotel.repository.HotelRepo;

@Service
public class HotelService {

    @Autowired
    HotelRepo hotelRepo;

    public List<Hotel> getHotels(){
        return hotelRepo.findAll();
    }

    public Hotel getHotel(String nom) {
        return hotelRepo.findById(nom).orElseThrow(() -> new RuntimeException("Hôtel non trouvé"));
    }
    

    public void creeHotel(Hotel hotel){
        hotelRepo.save(hotel);
    }

    public void supprimerHotel(String param) {
        Hotel hotel = hotelRepo.findById(param).orElseThrow(() -> new RuntimeException("Hotel non trouvée"));
         hotelRepo.delete(hotel);
    }

}
