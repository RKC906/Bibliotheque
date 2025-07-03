package biblio.services.admin;

import biblio.entities.Pret;
import biblio.repository.admin.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PretService {
    @Autowired
    private PretRepository pretRepository;

    public List<Pret> getAllPrets() {
        return pretRepository.findAll();
    }

    public Pret savePret(Pret pret) {
        return pretRepository.save(pret);
    }

    public void deletePret(Integer id) {
        pretRepository.deleteById(id);
    }

    public Pret getPretById(Integer id) {
        return pretRepository.findById(id).orElse(null);
    }
}
