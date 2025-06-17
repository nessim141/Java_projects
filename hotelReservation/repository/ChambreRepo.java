package reservation.hotel.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import reservation.hotel.model.Chambre;

@Repository
public interface  ChambreRepo extends  JpaRepository<Chambre, Integer> {

    @Query("SELECT c FROM Chambre c WHERE c.status = :status")
    List<Chambre> findByStatus(@Param("status") String status);

    @Query("""
        SELECT c FROM Chambre c
        WHERE c.status = 'Disponible' OR c NOT IN (
            SELECT r.chambre FROM Reservation r
            WHERE r.dateEntree < :dateFin AND r.dateSortie > :dateDebut)
        """)
    List<Chambre> findChambresDisponiblesParPeriode(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
    
    
}
