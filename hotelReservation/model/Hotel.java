package reservation.hotel.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter

@Entity
public class Hotel {
    @Id
    @Column(length = 100)
    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<Chambre> chambres;

    

    

}
