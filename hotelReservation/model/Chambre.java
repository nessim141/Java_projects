package reservation.hotel.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter

@Entity
public class Chambre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer numero;
    private String type;
    private double prix;
    private String status;

    @OneToMany(mappedBy="chambre")
    private List<Reservation> reservations;
    
    @ManyToOne
    private Hotel hotel;
    
}
