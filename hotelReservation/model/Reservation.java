package reservation.hotel.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer num;
    private Date dateEntree;
    private Date dateSortie;

    @ManyToOne
    private Chambre chambre;




    
}
