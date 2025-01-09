package services;

import org.example.classes.Groupe;
import org.example.classes.Materiel;
import org.example.repository.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterielServiceImpl implements MaterielService{

    @Autowired
    private MaterielRepository materielRepository;
    @Override
    public void insertMateriel(Materiel materiel) {
        materielRepository.save(materiel);
    }

    @Override
    public void updateMateriel(Materiel materiel) {
        materielRepository.save(materiel);
    }

    @Override
    public void deleteMateriel(Materiel materiel) {
        materielRepository.delete(materiel);
    }

    @Override
    public List<Materiel> getAllMateriel() {
        return materielRepository.findAll();
    }

    @Override
    public Optional<Materiel> getMateriel(String id) {
        return materielRepository.findById(id);
    }

    @Override
    public List<Materiel> getAllMaterielsByGroupe(Groupe groupe) {
        List<Materiel> materiels = new ArrayList<>();
        materiels = materielRepository.findByGroupe(groupe);
        if(!materiels.isEmpty()){
            return materiels;
        }
        return null;
    }

    public List<Materiel> getMaterielsByIds(List<Long> materielIds) {
        return materielRepository.findByIdIn(materielIds);
    }
}
