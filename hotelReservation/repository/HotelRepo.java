package reservation.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reservation.hotel.model.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, String>{

    
}
