package services;

import org.example.classes.Groupe;
import org.example.classes.Materiel;

import java.util.List;
import java.util.Optional;

public interface MaterielService {
    void insertMateriel(Materiel materiel);
    void updateMateriel(Materiel materiel);
    void deleteMateriel(Materiel materiel);
    List<Materiel> getAllMateriel();
    Optional<Materiel> getMateriel(String id);

    List<Materiel> getAllMaterielsByGroupe(Groupe groupe);
    public List<Materiel> getMaterielsByIds(List<Long> materielIds);

}
