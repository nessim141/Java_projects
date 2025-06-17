package reservation.hotel.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reservation.hotel.model.Chambre;
import reservation.hotel.services.ChambreService;


@RestController
@CrossOrigin
@RequestMapping("/api/chambre")
public class ChambreController {
    
    @Autowired
    ChambreService chambreService;

    @PostMapping("/cree")
    public void creeChambre(@RequestBody Chambre chambre){
        chambreService.creeChambre(chambre);
    }

    @GetMapping("/")
    public List<Chambre> getToutesChambre() {
        return chambreService.getToutesChambre();
    }

    @GetMapping("/{id}")
    public Chambre getChambre(@PathVariable Integer id) {
        return chambreService.getChambre(id);
    }
    


    @GetMapping("/disponible/periode")
    public List<Chambre> findChambresDisponiblesParPeriode(@RequestParam ("debut") Date debut, @RequestParam ("fin") Date fin ){
        return chambreService.findChambresDisponiblesParPeriode(debut, fin);
    }
    
    @GetMapping("/disponible")
    public List<Chambre> findDisponible(){
        return chambreService.getDisponible();
    }



}
