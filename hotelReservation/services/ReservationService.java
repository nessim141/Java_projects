package reservation.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reservation.hotel.model.Reservation;
import reservation.hotel.repository.ReservationRepo;

@Service
public class ReservationService {
    
    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    ChambreService chambreService;

    public void creeReservation(Reservation reservation){
        chambreService.rendreIndisponible(reservation.getChambre().getNumero());
        reservationRepo.save(reservation);
    }

    public void annulerReservation(Integer id) {
        Reservation reservation = reservationRepo.findById(id).orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        chambreService.rendreDisponible(reservation.getChambre().getNumero());
        reservationRepo.delete(reservation);
    }

    public List<Reservation> getReservations() {
       return reservationRepo.findAll();
    }
}
