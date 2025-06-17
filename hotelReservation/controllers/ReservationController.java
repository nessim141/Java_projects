package reservation.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reservation.hotel.model.Reservation;
import reservation.hotel.services.ReservationService;

@RestController
@CrossOrigin
@RequestMapping("/api/reservation")
public class ReservationController {
    
    @Autowired
    ReservationService reservationService;

    @GetMapping("/")
    public List<Reservation> getReservations(){
        return  reservationService.getReservations();
    }


    @PostMapping("/cree")
    public void creeReservation(@RequestBody Reservation reservation){
        reservationService.creeReservation(reservation);
    }

    @DeleteMapping("/annuler")
    public void anuulerReservation(@RequestParam Integer num){
        reservationService.annulerReservation(num);
    }


}
