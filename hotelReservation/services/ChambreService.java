package reservation.hotel.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reservation.hotel.model.Chambre;
import reservation.hotel.repository.ChambreRepo;

@Service
public class ChambreService {
    
    @Autowired
    ChambreRepo chambrerepo;

    public void creeChambre(Chambre chambre){
        chambrerepo.save(chambre);
    }

    public List<Chambre> getToutesChambre(){
        return chambrerepo.findAll();
    }

    public Chambre getChambre(Integer numero){
        return chambrerepo.findById(numero).orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
    }

    public List<Chambre> getDisponible(){
        return chambrerepo.findByStatus("disponible");
    }

    public List<Chambre> findChambresDisponiblesParPeriode(Date debut, Date fin){
        return chambrerepo.findChambresDisponiblesParPeriode(debut, fin);
    }

    public void rendreDisponible(Integer num){
        Chambre chambre = getChambre(num);
        chambre.setStatus("disponible");
    }

    public void rendreIndisponible(Integer num){
        Chambre chambre = getChambre(num);
        chambre.setStatus("indisponible");
    }


    


    
    


}
