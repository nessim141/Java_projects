package reservation.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reservation.hotel.model.Reservation;


@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer>{
    
}
