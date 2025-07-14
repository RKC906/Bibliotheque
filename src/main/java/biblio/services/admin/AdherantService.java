package biblio.services.admin;

import biblio.entities.Adherant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherantService {
    @Autowired
    private biblio.repository.adherant.AdherantRepository adherantRepository;


    public List<Adherant> getAllAdherants() {
        return adherantRepository.findAll();
    }

    public List<Adherant> getAdherantAbonnes()
    {
        return adherantRepository.findAdherantAbonnes();
    }
}
